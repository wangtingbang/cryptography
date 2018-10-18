package me.sigh.cryptography.sample.symmetric;

import me.sigh.cryptography.sample.key.CryptKeyGenerator;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

public class SimpleDESTest {
    @Test
    public void test_des(){
        String key = null;

        try{
            key = CryptKeyGenerator.symmetricKeyGenerator("DES", 56);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("DES key: "+ key);
        Assert.assertNotNull(key);
        Assert.assertTrue(!StringUtils.isEmpty(key));

        String body = "hello world!";
        String encryption = null;
        try{
            SimpleDESEncryptor encryptor = new SimpleDESEncryptor();
            encryption = encryptor.process(body, key);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("DES encryption: " + encryption);
        Assert.assertNotNull(encryption);
        Assert.assertTrue(!StringUtils.isEmpty(encryption));

        String decryption = null;
        try{
            SimpleDESDecryptor decryptor = new SimpleDESDecryptor();
            decryption = decryptor.process(encryption, key);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("DES decryption: " + decryption);
        Assert.assertNotNull(decryption);
        Assert.assertTrue(!StringUtils.isEmpty(decryption));
        Assert.assertEquals(body, decryption);
    }
}
