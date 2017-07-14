package com.goldwarehouse.server.security;

import java.security.*;
/**
 * Created by David on 2017/7/12.
 */
public class RSAUtil {
    private final int KEY_LENGTH = 1024;

    private static RSAUtil instance = new RSAUtil();

    private KeyPair keyPair;

    private RSAUtil() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(KEY_LENGTH);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static RSAUtil getInstance() {
        return instance;
    }

    public PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    public PrivateKey getPrivateKey(){
        return keyPair.getPrivate();
    }

}
