package me.sigh.cryptography.sample.signature;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class SimpleECDSAVerifier {

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
    public boolean verify(String key, String data, String sign, String algorithm)
            throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {

        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(key));

        Provider provider = new BouncyCastleProvider();

        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", provider);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        Signature signature = Signature.getInstance(algorithm, provider);

        signature.initVerify(publicKey);
        signature.update(data.getBytes());
        return signature.verify(Base64.decodeBase64(sign));
    }
}
