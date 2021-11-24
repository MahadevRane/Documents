package ScreeningSDK.WebClients.APIWebClients;

import ScreeningSDK.EncryptionHelpers.*;

import java.io.ByteArrayInputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.X509EncodedKeySpec;

public class BaseAPIWebClient {

    AESEncryption aesEncryption;
    RSAEncryption rsaEncryption;
    SignatureUtil signatureUtil;
    EncryptionUtils encryptionUtils;

    public BaseAPIWebClient(){
        aesEncryption = new AESEncryption();
        rsaEncryption = new RSAEncryption();
        signatureUtil = new SignatureUtil();
        encryptionUtils = new EncryptionUtils();
    }

    public String[] EncryptRequestDataAndSessionKey(String requestData, byte[] publicKey){

        Key sessionKey = aesEncryption.GetSymmetricSessionKey();

        EncryptionDecryptionParams aesEncryptionParams = new EncryptionDecryptionParams();
        aesEncryptionParams.PlainText = requestData;
        aesEncryptionParams.SharedKey = sessionKey;

        String encryptedReqDataXML = aesEncryption.EncryptData(aesEncryptionParams);

        EncryptionDecryptionParams rsaEncryptionParams = new EncryptionDecryptionParams();
        rsaEncryptionParams.SharedKey = sessionKey;
        rsaEncryptionParams.PublicKey = publicKey;

        String encryptedSessionKey = rsaEncryption.EncryptData(rsaEncryptionParams);

        return new String[]{encryptedReqDataXML, encryptedSessionKey};

    }

    public String SignXMLData(String a64Request, byte[] privateKey, String privatePassword){
        EncryptionDecryptionParams signingParams = new EncryptionDecryptionParams();
        signingParams.PrivateKey = privateKey;
        signingParams.PrivateKeyPassword = privatePassword;
        signingParams.PlainText = a64Request;

        return rsaEncryption.SignXML(signingParams);
    }

    public void VerifySignature(String responseData, byte[] publicKeyBytes)
    {

        try{
            PublicKey publicKey = GetPublicKey(publicKeyBytes);
            if(!signatureUtil.VerifyXML(responseData,publicKey))
                throw new Exception("XML Not Verified");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public PublicKey GetPublicKey(byte[] publicKeyBytes){
        PublicKey publicKey = null;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(publicKeyBytes);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate)cf.generateCertificate(inputStream);

            PublicKey publicKey1 = cert.getPublicKey();

            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey1.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return publicKey;
    }

    public String DecryptResponse(String SessionKey, String privateKeyPassword, byte[] privateKeyBytes, String responseData){

        Key decryptedKey = encryptionUtils.DecryptDataAsymmetrically(SessionKey,privateKeyPassword,privateKeyBytes);

        return encryptionUtils.DecryptDataSymmetrically(decryptedKey, responseData);
    }

}
