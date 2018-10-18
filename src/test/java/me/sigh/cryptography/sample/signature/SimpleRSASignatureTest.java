package me.sigh.cryptography.sample.signature;

import me.sigh.cryptography.sample.key.CryptKeyGenerator;
import me.sigh.cryptography.sample.key.CryptKeyPair;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class SimpleRSASignatureTest {


    @Test
    public void test_sign_verify(){

        CryptKeyPair keyPair = null;
        try {
            keyPair = CryptKeyGenerator.ecdsaKeyPairGenerator("RSA", 512);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("RSA privateKey: " + keyPair.getPrivateKey());
        System.out.println("RSA publicKey: " + keyPair.getPublicKey());

        Assert.assertNotNull(keyPair);
        Assert.assertNotNull(keyPair.getPrivateKey());
        Assert.assertNotNull(keyPair.getPublicKey());
        Assert.assertNotEquals(keyPair.getPublicKey(), keyPair.getPrivateKey());

        String body = "hello world!";
        String sign = null;

        try {
            SimpleRSASignature signature = new SimpleRSASignature();
            sign = signature.sign(body, keyPair.getPrivateKey());
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("RSA sign: " + sign);
        Assert.assertNotNull(sign);
        Assert.assertTrue(!StringUtils.isEmpty(sign));

        boolean verify = false;
        try{
            SimpleRSAVerifier verifier = new SimpleRSAVerifier();
            verify = verifier.verify(keyPair.getPublicKey(), body, sign);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("RSA verify: " + verify);
        Assert.assertTrue(verify);
    }
}
