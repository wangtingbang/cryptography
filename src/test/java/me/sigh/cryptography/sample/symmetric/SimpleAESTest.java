package me.sigh.cryptography.sample.symmetric;

import me.sigh.cryptography.sample.key.CryptKeyGenerator;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class SimpleAESTest {

    @Test
    public void test_aes(){
        String key = null;

        try{
            key = CryptKeyGenerator.symmetricKeyGenerator("AES", 128);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("AES key: "+ key);
        Assert.assertNotNull(key);
        Assert.assertTrue(!StringUtils.isEmpty(key));

        String body = "hello world!";
        String encryption = null;
        try{
            SimpleAESEncryptor encryptor = new SimpleAESEncryptor();
            encryption = encryptor.process(body, key);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("AES encryption: " + encryption);
        Assert.assertNotNull(encryption);
        Assert.assertTrue(!StringUtils.isEmpty(encryption));

        String decryption = null;
        try{
            SimpleAESDecryptor decryptor = new SimpleAESDecryptor();
            decryption = decryptor.process(encryption, key);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("AES decryption: " + decryption);
        Assert.assertNotNull(decryption);
        Assert.assertTrue(!StringUtils.isEmpty(decryption));
        Assert.assertEquals(body, decryption);
    }
}
