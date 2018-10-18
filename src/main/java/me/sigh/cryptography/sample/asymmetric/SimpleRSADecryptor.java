package me.sigh.cryptography.sample.asymmetric;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import sun.misc.BASE64Decoder;

/**
 * @Author: sigh
 * @Date: 2018-十月-18
 * @Time: 14:54
 */
public class SimpleRSADecryptor{
  
      public String process(String body, String keyString) throws NoSuchAlgorithmException,
                                              NoSuchPaddingException,
                                              InvalidKeySpecException,
                                              InvalidKeyException,
                                              BadPaddingException,
                                              IllegalBlockSizeException,
                                              IOException {

        byte[] keyBytes;
        keyBytes = new BASE64Decoder().decodeBuffer(keyString);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Cipher cipher = Cipher.getInstance("RSA");

        Key key;

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        key = keyFactory.generatePublic(keySpec);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return  new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(body)));
    }
}