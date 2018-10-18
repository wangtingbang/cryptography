package me.sigh.cryptography.sample.signature;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class SimpleRSAVerifier {


    /**
     *
     * @param key public key String
     * @param data
     * @param sign
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public boolean verify(String key, String data, String sign)
            throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {

        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(key));

        Provider provider = new BouncyCastleProvider();

        KeyFactory keyFactory = KeyFactory.getInstance("RSA", provider);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        Signature signature = Signature.getInstance("MD5withRSA", provider);

        signature.initVerify(publicKey);
        signature.update(data.getBytes());
        return signature.verify(Base64.decodeBase64(sign));
    }
}
