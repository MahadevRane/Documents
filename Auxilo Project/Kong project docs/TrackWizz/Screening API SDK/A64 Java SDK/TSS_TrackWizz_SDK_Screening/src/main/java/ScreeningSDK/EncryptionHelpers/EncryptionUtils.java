package ScreeningSDK.EncryptionHelpers;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Base64;

public class EncryptionUtils {

    public Key DecryptDataAsymmetrically(String sessionKey, String privatePassword, byte[] privateKeyBytes){
        Key SessionKey = null;
        try {

            ByteArrayInputStream inputStream = new ByteArrayInputStream(privateKeyBytes);
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load( inputStream,privatePassword.toCharArray());
            PrivateKey privateKey = (PrivateKey) keyStore.getKey("1", privatePassword.toCharArray());

            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] encodeKey = Base64.getDecoder().decode(sessionKey.getBytes());
            byte[] s = c.doFinal(encodeKey);

            byte[] decodedSessionKey = Base64.getDecoder().decode(s);

            SessionKey = new SecretKeySpec(decodedSessionKey, "AES");

//            SessionKeyString = Base64.getEncoder().encodeToString(SessionKey.getEncoded());

        }catch (Exception e){
            e.printStackTrace();
        }

        return SessionKey;

    }

    public String DecryptDataSymmetrically(Key sessionKey, String encryptedData){
        String decryptedText = "";
        try {
            byte[] decodeData = Base64.getDecoder().decode(encryptedData);

            Cipher Decipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            Decipher.init(Cipher.DECRYPT_MODE, sessionKey);

            byte[] cipherText = Decipher.doFinal(decodeData);
//            System.out.println("Main Response=====>" + new String(cipherText));
            decryptedText = new String(cipherText);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return decryptedText;
    }
}
