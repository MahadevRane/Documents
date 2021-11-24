package ScreeningSDK.Models.A64.Common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

public class BaseScreeningApiResult {

    public int AlertCount;
    public String ReportData;
    public Alerts Alerts;

    public BaseScreeningApiResult(){
        Alerts = new Alerts();
    }

}
