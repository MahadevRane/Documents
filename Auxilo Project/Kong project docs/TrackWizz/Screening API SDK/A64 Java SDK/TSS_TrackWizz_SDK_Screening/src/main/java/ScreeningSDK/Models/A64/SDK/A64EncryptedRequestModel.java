package ScreeningSDK.Models.A64.SDK;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "A64EncryptedRequestModel")
@XmlAccessorType(XmlAccessType.FIELD)
public class A64EncryptedRequestModel {

    public String ApiToken;
    public String SessionKey;
    public String RequestData;

    public String getApiToken() {
        return ApiToken;
    }

    public void setApiToken(String apiToken) {
        ApiToken = apiToken;
    }

    public String getSessionKey() {
        return SessionKey;
    }

    public void setSessionKey(String sessionKey) {
        SessionKey = sessionKey;
    }

    public String getRequestData() {
        return RequestData;
    }

    public void setRequestData(String requestData) {
        RequestData = requestData;
    }
}
