package ScreeningSDK.Models.A64.SDK;


import ScreeningSDK.Models.BaseEncryptedResponseModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "A64EncryptedResponseModel")
@XmlAccessorType(XmlAccessType.FIELD)
public class A64EncryptedResponseModel extends BaseEncryptedResponseModel {

    public String ResponseData;

    @Override
    public String toString() {
        return "A64EncryptedResponseModel{" +
                "apiToken='" + apiToken + '\'' +
                ", RequestId='" + RequestId + '\'' +
                ", SessionKey='" + SessionKey + '\'' +
                ", RejectionCode='" + RejectionCode + '\'' +
                ", RejectionMessage='" + RejectionMessage + '\'' +
                ", RequestStatus='" + RequestStatus + '\'' +
                ", ResponseData='" + ResponseData + '\'' +
                '}';
    }

}
