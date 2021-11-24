package ScreeningSDK.Models;


import ScreeningSDK.Enums.RequestStatusEnum;

public class BaseClientResponse {

    public String ApiToken;
    public String RequestId;
    public String SessionKey;
    public RequestStatusEnum RequestStatus;
    public String ErrorCode;
    public String ErrorMessage;

}
