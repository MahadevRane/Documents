package ScreeningSDK.Models.A64.SDK;

import ScreeningSDK.Models.A64.Common.BaseScreeningApiResult;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ScreeningResults")
public class A64InternalScreeningApiResult extends BaseScreeningApiResult {

    public String ResponseId;
    public String RequestId;
    public String Matched;
    public String ApiToken;
    public String RejectionMessage;
    public String RejectionCode;

    @Override
    public String toString() {
        return "A64InternalScreeningApiResult{" +
                "AlertCount=" + AlertCount +
                ", ReportData='" + ReportData + '\'' +
                ", Alerts=" + Alerts +
                ", ResponseId='" + ResponseId + '\'' +
                ", RequestId='" + RequestId + '\'' +
                ", Matched='" + Matched + '\'' +
                ", ApiToken='" + ApiToken + '\'' +
                ", RejectionMessage='" + RejectionMessage + '\'' +
                ", RejectionCode='" + RejectionCode + '\'' +
                '}';
    }
}
