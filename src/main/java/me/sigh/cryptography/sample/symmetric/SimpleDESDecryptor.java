package me.sigh.cryptography.sample.symmetric;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;

/**
 * @Author: sigh
 * @Date: 2018-十月-18
 * @Time: 14:54
 */
public class SimpleDESDecryptor{

  public String process(String data, String key)
          throws NoSuchPaddingException,
                  NoSuchAlgorithmException,
                  InvalidKeyException,
                  BadPaddingException,
                  IllegalBlockSizeException,
                  IOException{

      byte[] keyBytes;
      keyBytes = new BASE64Decoder().decodeBuffer(key);
      Key k = new SecretKeySpec(keyBytes, "DES");
      Cipher cipher = Cipher.getInstance("DES");
      cipher.init(Cipher.DECRYPT_MODE, k);
      return new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(data)));
  }
}