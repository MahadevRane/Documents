package ScreeningSDK.EncryptionHelpers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class XMLUtils {

    public String ToXML(Object requestData)
    {
        String xmlString = "";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(requestData.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML

            //Print XML String to Console
            StringWriter sw = new StringWriter();

            //Write XML to StringWriter
            jaxbMarshaller.marshal(requestData, sw);

            //Verify XML Content
            xmlString =  sw.toString();

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return xmlString;
    }

    public Object ToObject(String xmlResponse, Object obj){

        JAXBContext jaxbContext;
        Object a64EncryptedResponseModel = null;
        try
        {
        jaxbContext = JAXBContext.newInstance(obj.getClass());

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

//            a64EncryptedResponseModel = jaxbUnmarshaller.unmarshal(new FileInputStream(xmlFile));
            a64EncryptedResponseModel = jaxbUnmarshaller.unmarshal(new StringReader(xmlResponse));

        }
        catch (JAXBException e)
        {
            e.printStackTrace();
        }

        return a64EncryptedResponseModel;

    }

/*    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-16\"?>\n" +
                "<A64ResponseModel xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" \n" +
                "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                " <ScreeningResults>\n" +
                " <ResponseId>88</ResponseId>\n" +
                " <RequestId>123</RequestId>\n" +
                " <Matched>Match</Matched>\n" +
                " <AlertCount>1</AlertCount>\n" +
                " <Alerts>\n" +
                " <Alert>\n" +
                " <SrNo>1</SrNo>\n" +
                " <Source>WorldCheck</Source>\n" +
                " <SourceUniqueId>1839237</SourceUniqueId>\n" +
                " <TrackwizzId>3775547</TrackwizzId>\n" +
                " <PrimaryMatch>A24 - Name Vowel Matching</PrimaryMatch>\n" +
                " <MatchType>Probable</MatchType>\n" +
                " <Score></Score>\n" +
                " </Alert>\n" +
                " </Alerts>\n" +
                " <ApiToken>fa825d85-57bc-4894-915e-de70c24458db</ApiToken>\n" +
                " <ReportData>isce5Z9fGGZxs09p8kB9SeYW4GmAEvwpk</ReportData>\n" +
                " <RejectionCode></RejectionCode>\n" +
                " <RequestStatus></RequestStatus>\n" +
                " <RejectionMessage></RejectionMessage>\n" +
                " </ScreeningResults>\n" +
                "</A64ResponseModel>";

        XMLUtils xmlUtils = new XMLUtils();

        A64InternalResponseModel a64InternalResponseModel = (A64InternalResponseModel) xmlUtils.ToObject(xml, new A64InternalResponseModel());

        a64InternalResponseModel.ScreeningResults.Alerts.Alert.forEach(alert -> {
            System.out.println(alert.toString());
        });
    }
*/
}
