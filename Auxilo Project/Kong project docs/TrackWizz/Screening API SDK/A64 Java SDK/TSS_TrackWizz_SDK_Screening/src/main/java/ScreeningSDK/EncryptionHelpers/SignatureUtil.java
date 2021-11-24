package ScreeningSDK.EncryptionHelpers;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SignatureUtil {

    public String SignXML(String a64Request, PrivateKey privateKey, X509Certificate x509Certificate) {
        String xmlSignedString = "";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream inputStream = new ByteArrayInputStream(a64Request.getBytes());
            Document doc = builder.parse(inputStream);

            DOMSignContext dsc = new DOMSignContext(privateKey, doc.getDocumentElement());
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

            Reference ref = fac.newReference("", fac.newDigestMethod(DigestMethod.SHA1, null), Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)), null, null);
            SignedInfo si = fac.newSignedInfo(fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null), fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null), Collections.singletonList(ref));

            KeyInfoFactory kif = fac.getKeyInfoFactory();
            List<Object> X509Content = new ArrayList<>();
            X509Content.add(x509Certificate.getSubjectX500Principal().getName());
            kif.newX509Data(X509Content);
            KeyInfo ki = kif.newKeyInfo(Collections.singletonList(kif.newX509Data(Collections.singletonList(x509Certificate))));

            XMLSignature signature = fac.newXMLSignature(si, ki);
            signature.sign(dsc);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            StringWriter writer = new StringWriter();
            trans.transform(new DOMSource(doc), new StreamResult(writer));

            xmlSignedString = writer.getBuffer().toString();

        }catch (Exception e){
            e.printStackTrace();
        }

        return xmlSignedString;

    }

    public boolean VerifyXML(String xmlSignedString, PublicKey publicKey){

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputStream inputStream = new ByteArrayInputStream(xmlSignedString.getBytes());
            Document doc = builder.parse(inputStream);
            NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
            if (nl.getLength() == 0) {
                throw new Exception("No XML Digital Signature Found, document is discarded");
            }
            DOMValidateContext valContext = new DOMValidateContext(publicKey, nl.item(0));
            XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
            XMLSignature signature = fac.unmarshalXMLSignature(valContext);

            if(signature.validate(valContext))
                return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
