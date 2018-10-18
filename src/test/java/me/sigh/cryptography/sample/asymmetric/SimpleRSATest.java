package me.sigh.cryptography.sample.asymmetric;

import me.sigh.cryptography.sample.key.CryptKeyGenerator;
import me.sigh.cryptography.sample.key.CryptKeyPair;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: wangtingbang
 * @Date: 2018-Oct-18
 * @Time: 15:31
 */
public class SimpleRSATest {


    @Test
    public void test_rsa_crypt(){

        CryptKeyPair keyPair = null;

        try {
            keyPair = CryptKeyGenerator.asymmetricKeyPairGenerator("RSA", 512);
        }catch (Exception e){
            e.printStackTrace();
        }

        Assert.assertNotNull(keyPair);

        System.out.println("privateKey: " + keyPair.getPrivateKey());
        System.out.println("publicKey: " + keyPair.getPublicKey());

        String body = "hello world!";
        String encryption = null;

        try {
            SimpleRSAEncryptor encryptor =  new SimpleRSAEncryptor();
            encryption = encryptor.process(body, keyPair.getPrivateKey());
        }catch (Exception e){

            e.printStackTrace();
        }

        Assert.assertNotNull(encryption);

        String decryption = null;
        try{
            SimpleRSADecryptor decryptor = new SimpleRSADecryptor();
            decryption = decryptor.process(encryption, keyPair.getPublicKey());
        }catch (Exception e){
            e.printStackTrace();
        }

        Assert.assertNotNull(decryption);

        Assert.assertEquals(body, decryption);

    }
}