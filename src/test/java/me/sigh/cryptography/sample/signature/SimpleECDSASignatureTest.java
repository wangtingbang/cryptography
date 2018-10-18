package me.sigh.cryptography.sample.signature;

import me.sigh.cryptography.sample.key.CryptKeyGenerator;
import me.sigh.cryptography.sample.key.CryptKeyPair;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class SimpleECDSASignatureTest {


    @Test
    public void test_sign_verify(){

        CryptKeyPair keyPair = null;
        try {
            keyPair = CryptKeyGenerator.ecdsaKeyPairGenerator("ECDSA", 256);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("privateKey: " + keyPair.getPrivateKey());
        System.out.println("publicKey: " + keyPair.getPublicKey());

        Assert.assertNotNull(keyPair);
        Assert.assertNotNull(keyPair.getPrivateKey());
        Assert.assertNotNull(keyPair.getPublicKey());
        Assert.assertNotEquals(keyPair.getPublicKey(), keyPair.getPrivateKey());

        String body = "hello world!";
        String sign = null;

        try {
            SimpleECDSASignature signature = new SimpleECDSASignature();
            sign = signature.sign(keyPair.getPrivateKey(), body, "ECDSA");
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("ECDSA sign: " + sign);
        Assert.assertNotNull(sign);
        Assert.assertTrue(!StringUtils.isEmpty(sign));

        boolean verify = false;
        try{
            SimpleECDSAVerifier verifier = new SimpleECDSAVerifier();
            verify = verifier.verify(keyPair.getPublicKey(), body, sign, "ECDSA");
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("ECDSA verify: " + verify);

        Assert.assertTrue(verify);
    }
}
