package me.sigh.cryptography.sample.key;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author: wangtingbang
 * @Date: 2018-Oct-18
 * @Time: 15:31
 */
public class CryptKeyGeneratorTest {


    @Test
    public void test_asymmetricKeyPairGenerator() {

        CryptKeyPair keyPair = null;
        try {
            keyPair = CryptKeyGenerator.asymmetricKeyPairGenerator("RSA", 1024);
        } catch (Exception e) {

            e.printStackTrace();
        }

        System.out.println("privateKey: " + keyPair.getPrivateKey());
        System.out.println("publicKey: " + keyPair.getPublicKey());

        Assert.assertNotNull(keyPair);
        Assert.assertNotNull(keyPair.getPrivateKey());
        Assert.assertNotNull(keyPair.getPublicKey());
        Assert.assertNotEquals(keyPair.getPublicKey(), keyPair.getPrivateKey());
    }

    @Test
    public void test_symmetricKeyPairGenerator_DES() {

        String key = null;
        try {
            key = CryptKeyGenerator.symmetricKeyGenerator("DES", 56);
        } catch (Exception e) {

            e.printStackTrace();
        }

        System.out.println("key: " + key);

        Assert.assertNotNull(key);
        Assert.assertTrue(!StringUtils.isEmpty(key));
    }

    @Test
    public void test_symmetricKeyPairGenerator_AES() {

        String key = null;
        try {
            key = CryptKeyGenerator.symmetricKeyGenerator("AES", 128);
        } catch (Exception e) {

            e.printStackTrace();
        }

        System.out.println("key: " + key);

        Assert.assertNotNull(key);
        Assert.assertTrue(!StringUtils.isEmpty(key));
    }
}
