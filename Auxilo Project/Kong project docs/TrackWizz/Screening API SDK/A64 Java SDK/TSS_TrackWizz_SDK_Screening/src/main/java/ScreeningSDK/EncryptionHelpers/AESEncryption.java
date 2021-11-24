package ScreeningSDK.EncryptionHelpers;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

public class AESEncryption {

    private Key sessionKey;
    private String encryptedData;

    public Key GetSymmetricSessionKey() {

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = new SecureRandom();
            int keyBitSize = 256;
            keyGenerator.init(keyBitSize, secureRandom);
            sessionKey = keyGenerator.generateKey();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return sessionKey;
    }


    public String EncryptData(EncryptionDecryptionParams aesEncryptionParams){
        try{

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesEncryptionParams.SharedKey);

            byte[] cipherText = cipher.doFinal(aesEncryptionParams.PlainText.getBytes());

            encryptedData =  Base64.getEncoder()
                    .encodeToString(cipherText);

        }catch (Exception e){
            e.printStackTrace();
        }
        return encryptedData;
    }
}
