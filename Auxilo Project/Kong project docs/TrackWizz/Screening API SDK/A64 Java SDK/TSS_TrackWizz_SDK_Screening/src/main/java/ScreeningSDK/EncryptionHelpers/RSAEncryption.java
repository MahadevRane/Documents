package ScreeningSDK.EncryptionHelpers;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.security.*;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAEncryption {

    String encryptedKey;
    SignatureUtil signatureUtil;

    public RSAEncryption(){
        signatureUtil = new SignatureUtil();
    }

    public String EncryptData(EncryptionDecryptionParams rsaEncryptionParams){
        try{

            ByteArrayInputStream inputStream = new ByteArrayInputStream(rsaEncryptionParams.PublicKey);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate)cf.generateCertificate(inputStream);

            PublicKey publicKey1 = cert.getPublicKey();

            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey1.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            Key publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[]  encodeKey = rsaEncryptionParams.SharedKey.getEncoded();
            byte[] base64 = Base64.getEncoder().encode(encodeKey);

            byte[] s = c.doFinal(base64);

            encryptedKey = Base64.getEncoder()
                    .encodeToString(s);

        }catch (Exception e){
            e.printStackTrace();
        }
        return encryptedKey;
    }

    public String SignXML(EncryptionDecryptionParams signingParams){

        String signedXML = "";

        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(signingParams.PrivateKey);
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load( inputStream,signingParams.PrivateKeyPassword.toCharArray());
            PrivateKey privateKey = (PrivateKey) keyStore.getKey("1", signingParams.PrivateKeyPassword.toCharArray());
            X509Certificate x509Certificate = (X509Certificate) keyStore.getCertificate("1");

            byte[] privateEncoded = privateKey.getEncoded();
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateEncoded);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey PrivateSignKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

            signedXML = signatureUtil.SignXML(signingParams.PlainText, PrivateSignKey, x509Certificate);

        }catch (Exception e){
            e.printStackTrace();
        }
        return signedXML;
    }
}
