package ScreeningSDK.Models.A64.Common;

public class Alert {
    public int SrNo;
    public String Source;
    public String SourceUniqueId;
    public String TrackwizzId;
    public String PrimaryMatch;
    public String MatchType;
    public String Score;

    @Override
    public String toString() {
        return "Alert{" +
                "SrNo=" + SrNo +
                ", Source='" + Source + '\'' +
                ", SourceUniqueId='" + SourceUniqueId + '\'' +
                ", TrackwizzId='" + TrackwizzId + '\'' +
                ", PrimaryMatch='" + PrimaryMatch + '\'' +
                ", MatchType='" + MatchType + '\'' +
                ", Score='" + Score + '\'' +
                '}';
    }
}
