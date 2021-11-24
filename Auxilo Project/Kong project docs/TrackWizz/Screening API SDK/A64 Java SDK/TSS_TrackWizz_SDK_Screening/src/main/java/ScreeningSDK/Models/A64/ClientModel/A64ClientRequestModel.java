package ScreeningSDK.Models.A64.ClientModel;

public class A64ClientRequestModel {
     String apiToken;
     byte[] privateKey;
     byte[] publicKey;
     String privatePassword;
     String apiURL;
     A64RequestData requestModel;
     String RequestId;

    public A64ClientRequestModel(String apiToken, byte[] privateKey, byte[] publicKey, String privatePassword,A64RequestData a64RequestData, String apiURL) {

        this.apiToken = apiToken;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.privatePassword = privatePassword;
        this.apiURL = apiURL;
        this.requestModel = a64RequestData;
        this.RequestId = a64RequestData.RequestId;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivatePassword() {
        return privatePassword;
    }

    public void setPrivatePassword(String privatePassword) {
        this.privatePassword = privatePassword;
    }

    public String getApiURL() {
        return apiURL;
    }

    public void setApiURL(String apiURL) {
        this.apiURL = apiURL;
    }

    public A64RequestData getRequestModel() {
        return requestModel;
    }

    public void setRequestModel(A64RequestData requestModel) {
        this.requestModel = requestModel;
    }

}
