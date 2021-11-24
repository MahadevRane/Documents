package ScreeningSDK.WebClients.APIWebClients;

import ScreeningSDK.EncryptionHelpers.XMLUtils;
import ScreeningSDK.Enums.Nationality;
import ScreeningSDK.Enums.RequestStatusEnum;
import ScreeningSDK.Enums.ScreeningMatchingResultEnum;
import ScreeningSDK.Models.A64.ClientModel.*;
import ScreeningSDK.Models.A64.Common.*;
import ScreeningSDK.Models.A64.SDK.*;
import ScreeningSDK.WebClients.TWWebClient;

import java.io.*;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class A64WebClient extends BaseAPIWebClient {

    XMLUtils xmlUtils;

    public A64WebClient(){
        xmlUtils = new XMLUtils();
    }

    public A64ClientResponseModel Execute(A64ClientRequestModel a64ClientRequestModel){

        try {
            //validateInput

            A64EncryptedRequestModel a64EncryptedRequestModel = new A64EncryptedRequestModel();

            String signedRequest = GetSignedRequest(a64ClientRequestModel,a64EncryptedRequestModel);
            String xmlResponse = new TWWebClient().WebClientPost(signedRequest,a64ClientRequestModel.getApiURL());
            return VerifySignAndReturnResponse(a64ClientRequestModel,xmlResponse);

        } catch (Exception e){
            e.printStackTrace();
            return CreateRequestFailureResponse(a64ClientRequestModel,e);
        }

    }

    public CompletableFuture<A64ClientResponseModel> ExecuteAsync(A64ClientRequestModel a64ClientRequestModel){
        CompletableFuture<A64ClientResponseModel> clientResponseModelCompletableFuture = null;
        try {
            //validateInput

            A64EncryptedRequestModel a64EncryptedRequestModel = new A64EncryptedRequestModel();

            String signedRequest = GetSignedRequest(a64ClientRequestModel,a64EncryptedRequestModel);

            CompletableFuture<HttpResponse<String>> xmlResponse = new TWWebClient().WebClientPostAsync(signedRequest,a64ClientRequestModel.getApiURL());

            clientResponseModelCompletableFuture = CompletableFuture.supplyAsync(() -> {
                try {
                    return VerifySignAndReturnResponse(a64ClientRequestModel,xmlResponse.get().body());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            });

        } catch (Exception e){
            e.printStackTrace();
        }
        return clientResponseModelCompletableFuture;

    }

    private String GetSignedRequest(A64ClientRequestModel a64ClientRequestModel, A64EncryptedRequestModel a64EncryptedRequestModel){

        String requestData = xmlUtils.ToXML(a64ClientRequestModel.getRequestModel());
        String[] KeyAndData = EncryptRequestDataAndSessionKey(requestData,a64ClientRequestModel.getPublicKey());

        a64EncryptedRequestModel.setApiToken(a64ClientRequestModel.getApiToken());
        a64EncryptedRequestModel.setRequestData(KeyAndData[0]);
        a64EncryptedRequestModel.setSessionKey(KeyAndData[1]);

        String a64RequestXML = xmlUtils.ToXML(a64EncryptedRequestModel);

        return SignXMLData(a64RequestXML,a64ClientRequestModel.getPrivateKey(),a64ClientRequestModel.getPrivatePassword());
    }

    private A64ClientResponseModel VerifySignAndReturnResponse(A64ClientRequestModel a64ClientRequestModel, String xmlResponse) {

        VerifySignature(xmlResponse, a64ClientRequestModel.getPublicKey());
        A64EncryptedResponseModel a64EncryptedResponseModel = (A64EncryptedResponseModel) xmlUtils.ToObject(xmlResponse, new A64EncryptedResponseModel());

        return FillA64OuterResponseModel(a64EncryptedResponseModel, a64ClientRequestModel);
    }

    private A64ClientResponseModel FillA64OuterResponseModel(A64EncryptedResponseModel a64EncryptedResponseModel, A64ClientRequestModel a64ClientRequestModel){
        A64ClientResponseModel a64ClientResponseModel = new A64ClientResponseModel();

        a64ClientResponseModel.ApiToken = a64EncryptedResponseModel.apiToken;
        a64ClientResponseModel.RequestId = a64EncryptedResponseModel.RequestId;
        a64ClientResponseModel.SessionKey = a64EncryptedResponseModel.SessionKey;
        a64ClientResponseModel.ErrorCode = a64EncryptedResponseModel.RejectionCode;
        a64ClientResponseModel.ErrorMessage = a64EncryptedResponseModel.RejectionMessage;
        boolean isAccepted = true;

        if(a64EncryptedResponseModel.RequestStatus != null){
            isAccepted = !a64EncryptedResponseModel.RequestStatus.equalsIgnoreCase("Rejected by TW");
           a64ClientResponseModel.RequestStatus = isAccepted ? RequestStatusEnum.Accepted_By_TrackWizz : RequestStatusEnum.Rejected_By_TrackWizz;
        }

        if(isAccepted){

            A64InternalResponseModel a64InternalResponseModel = null;
            String decryptedResponseXML = DecryptResponse(a64EncryptedResponseModel.SessionKey, a64ClientRequestModel.getPrivatePassword(), a64ClientRequestModel.getPrivateKey(), a64EncryptedResponseModel.ResponseData);

            try{
                a64InternalResponseModel = (A64InternalResponseModel) xmlUtils.ToObject(decryptedResponseXML, new A64InternalResponseModel());
            }catch (Exception e){
                a64ClientResponseModel.RequestStatus = RequestStatusEnum.UnExpected_Error;
                a64ClientResponseModel.ErrorMessage = e.getMessage();
            }

            if(a64InternalResponseModel != null){
                a64ClientResponseModel.ErrorMessage += a64InternalResponseModel.ErrorMessage;
                a64ClientResponseModel.ErrorCode += a64InternalResponseModel.ErrorCode;
                if(a64InternalResponseModel.ScreeningResults != null){
                    a64ClientResponseModel.a64ScreeningApiResult.ResponseId = a64InternalResponseModel.ScreeningResults.ResponseId;
                    a64ClientResponseModel.a64ScreeningApiResult.Matched = GetMatchingResultEnum(a64InternalResponseModel.ScreeningResults.Matched);
                    a64ClientResponseModel.a64ScreeningApiResult.AlertCount = a64InternalResponseModel.ScreeningResults.AlertCount;
                    a64ClientResponseModel.ErrorCode += a64InternalResponseModel.ScreeningResults.RejectionCode;
                    a64ClientResponseModel.ErrorMessage += a64InternalResponseModel.ScreeningResults.RejectionMessage;
                    a64ClientResponseModel.a64ScreeningApiResult.Alerts = a64InternalResponseModel.ScreeningResults.Alerts;
                    a64ClientResponseModel.a64ScreeningApiResult.ReportData = a64InternalResponseModel.ScreeningResults.ReportData;
                }
            }
        }

       return a64ClientResponseModel;
    }

    private A64ClientResponseModel CreateRequestFailureResponse(A64ClientRequestModel a64ClientRequestModel, Exception e){
        A64ClientResponseModel a64OuterResponseModel = new A64ClientResponseModel();

        a64OuterResponseModel.ApiToken = a64ClientRequestModel.getApiToken();
        a64OuterResponseModel.RequestId = a64ClientRequestModel.getRequestId();
        a64OuterResponseModel.ErrorMessage = e.getMessage();
        a64OuterResponseModel.RequestStatus = RequestStatusEnum.UnExpected_Error;

        return a64OuterResponseModel;
    }

    private ScreeningMatchingResultEnum GetMatchingResultEnum(String match){
        if(!match.isEmpty()){
            if(match.equalsIgnoreCase("Match"))
                return ScreeningMatchingResultEnum.Match;
            else if(match.equalsIgnoreCase("Not Match"))
                return ScreeningMatchingResultEnum.Not_Match;
            else
                return ScreeningMatchingResultEnum.Error;
        }
        return ScreeningMatchingResultEnum.Error;
    }

    private static void main(String[] args) throws Exception {

        A64WebClient a64WebClient = new A64WebClient();

        FileInputStream publicPath = new FileInputStream("");

        byte[] publicKeyBytes = publicPath.readAllBytes();

        InputStream privatePath = new FileInputStream("");

        byte[] privateKeyBytes = privatePath.readAllBytes();

        A64RequestData a64RequestData = a64WebClient.getRequestData();

        A64ClientRequestModel a64ClientRequestModel = a64WebClient.GetA64ClientRequestModel(publicKeyBytes,privateKeyBytes,a64RequestData);

//        CompletableFuture<A64ClientResponseModel> res = a64WebClient.ExecuteAsync(a64ClientRequestModel);

        //      A64ClientResponseModel a64ClientResponseModel = res.get();

//        A64ClientResponseModel a64ClientResponseModel = a64WebClient.Execute(a64ClientRequestModel);
    }

    A64RequestData getRequestData(){

        A64RequestData a64RequestData = new A64RequestData();
        a64RequestData.RequestId = "112233";
        a64RequestData.ParentCompany = "SC115";
        a64RequestData.FirstName = "dawood";
        a64RequestData.LastName = "Ibrahim";
        a64RequestData.CustomerCategory = "IND";

        Nationalities nationalities = new Nationalities();
        nationalities.Nationality.add(Nationality.IN);
        nationalities.Nationality.add(Nationality.AE);
        nationalities.Nationality.add(Nationality.BE);

        a64RequestData.Nationalities = nationalities;

        Tags tags = new Tags();

        tags.Tag.add("1");
        tags.Tag.add("3");
        tags.Tag.add("4");

        ProductSegments productSegments = new ProductSegments();

        productSegments.ProductSegment.add("BSE_CASH");
        productSegments.ProductSegment.add("NSE_FNO");
        productSegments.ProductSegment.add("Other");

        a64RequestData.ProductSegments = productSegments;
        a64RequestData.Tags = tags;
        return a64RequestData;
    }

    A64ClientRequestModel GetA64ClientRequestModel(byte[] publicKeyBytes,byte[] privateKeyBytes, A64RequestData a64RequestData) throws Exception {
        String apiToken = "11ac8347-2093-42aa-a3ac-29f7566537a7";
        String privatePassword = "R@12345";
        String apiURL = "https://mlapidemo1.tssconsultancy.com:54322/crmapi/a64screeningapi/GetScreeningResult";

        return new A64ClientRequestModel(apiToken, privateKeyBytes, publicKeyBytes,privatePassword,a64RequestData,apiURL);
    }



}
