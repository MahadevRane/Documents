package ScreeningSDK.EncryptionHelpers;

import java.security.Key;

public class EncryptionDecryptionParams {
    public byte[] PublicKey;
    public byte[] PrivateKey;
    public String PrivateKeyPassword;
    public String PlainText;
    public String CipherText;
    public Key SharedKey;
    public String MachinePath;
    public String CipherMode;

}
