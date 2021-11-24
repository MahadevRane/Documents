package ScreeningSDK.Models.A64.Common;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

public class Alerts {

    public List<Alert> Alert;

    Alerts(){
        Alert = new ArrayList<>();
    }

}
