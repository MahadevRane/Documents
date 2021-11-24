package ScreeningSDK.Models.A64.ClientModel;

import ScreeningSDK.Enums.ScreeningMatchingResultEnum;
import ScreeningSDK.Models.A64.Common.BaseScreeningApiResult;

public class A64ScreeningApiResult extends BaseScreeningApiResult {

    public String ResponseId;
    public String RequestId;
    public String ApiToken;
    public String RejectionMessage;
    public String RejectionCode;
    public String RequestStatus;
    public ScreeningMatchingResultEnum Matched;

    @Override
    public String toString() {
        return "A64ScreeningApiResult{" +
                "ResponseId='" + ResponseId + '\'' +
                ", RequestId='" + RequestId + '\'' +
                ", ApiToken='" + ApiToken + '\'' +
                ", RejectionMessage='" + RejectionMessage + '\'' +
                ", RejectionCode='" + RejectionCode + '\'' +
                ", Matched=" + Matched +
                ", AlertCount=" + AlertCount +
                ", Alerts=" + Alerts.toString() +
                ", ReportData='" + ReportData + '\'' +
                "RequestStatus + " + RequestStatus;
    }
}
