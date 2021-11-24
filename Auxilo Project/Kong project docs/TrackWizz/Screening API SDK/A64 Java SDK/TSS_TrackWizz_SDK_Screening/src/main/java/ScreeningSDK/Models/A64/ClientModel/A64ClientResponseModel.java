package ScreeningSDK.Models.A64.ClientModel;


import ScreeningSDK.Models.BaseClientResponse;

public class A64ClientResponseModel extends BaseClientResponse {

    public A64ScreeningApiResult a64ScreeningApiResult;

    public A64ClientResponseModel(){
        a64ScreeningApiResult = new A64ScreeningApiResult();
    }
    /*
    *     @Override
    public String toString() {
        return "A64ClientResponseModel{" +
                "a64ScreeningApiResult=" + a64ScreeningApiResult +
                ", apiToken='" + ApiToken + '\'' +
                ", RequestId='" + RequestId + '\'' +
                ", SessionKey='" + SessionKey + '\'' +
                ", RejectionCode='" + RejectionCode + '\'' +
                ", RejectionMessage='" + RejectionMessage + '\'' +
                ", RequestStatus='" + RequestStatus + '\'' +
                '}';
    }

    * */
}
