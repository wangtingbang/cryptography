package me.sigh.cryptography.sample.signature;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class SimpleRSASignature {

    public String sign(String body, String keyString) throws NoSuchAlgorithmException,
            InvalidKeySpecException,
            InvalidKeyException,
            SignatureException,
            IOException {

        byte[] keyBytes;
        keyBytes = new BASE64Decoder().decodeBuffer(keyString);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        Signature signature = Signature.getInstance("MD5withRSA");

        signature.initSign(privateKey);
        signature.update(body.getBytes());
        return Base64.encodeBase64String(signature.sign());
    }
}
