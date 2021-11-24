package ScreeningSDK.Models.A64.SDK;

import ScreeningSDK.Models.A64.ClientModel.A64ScreeningApiResult;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="A64ResponseModel")
public class A64InternalResponseModel {

    public A64InternalScreeningApiResult ScreeningResults;

    public String ErrorCode;

    public String ErrorMessage;

    public String RequestStatus;
    /*
    @Override
    public String toString() {
        return "A64InternalResponseModel{" +
                "ScreeningResults=" + ScreeningResults.toString() +
                '}';
    }*/
}
