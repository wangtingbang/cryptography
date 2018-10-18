package me.sigh.cryptography.sample.key;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * @Author: sigh
 * @Date: 2018-十月-18
 * @Time: 14:54
 */
public class CryptKeyGenerator {

  /**
   * 生成非对称加密密钥
   */
  public static CryptKeyPair asymmetricKeyPairGenerator(String algorithm, int keySize)
      throws Exception {
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
    keyPairGenerator.initialize(keySize);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    CryptKeyPair pair = new CryptKeyPair();
    pair.setPrivateKey(Base64.encodeBase64String(keyPair.getPrivate().getEncoded()));
    pair.setPublicKey(Base64.encodeBase64String(keyPair.getPublic().getEncoded()));
    return pair;
  }

  /**
   * 生成对称加密密钥
   */
  public static String symmetricKeyGenerator(String algorithm, int keySize) throws Exception {

    Provider provider = new BouncyCastleProvider();
    KeyGenerator generator = KeyGenerator.getInstance(algorithm);
    generator.init(keySize);
    SecretKey secretKey = generator.generateKey();
    return Base64.encodeBase64String(secretKey.getEncoded());
  }

  /**
   * 生成椭圆验签密钥
   */
  public static CryptKeyPair ecdsaKeyPairGenerator(String algorithm, int keySize)
      throws Exception {
    Provider provider = new BouncyCastleProvider();
    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm, provider);
    keyPairGenerator.initialize(keySize);
    KeyPair keyPair = keyPairGenerator.generateKeyPair();
    CryptKeyPair pair = new CryptKeyPair();
    pair.setPrivateKey(Base64.encodeBase64String(keyPair.getPrivate().getEncoded()));
    pair.setPublicKey(Base64.encodeBase64String(keyPair.getPublic().getEncoded()));
    return pair;
  }

  public static void main(String[] args) {
    try {
      CryptKeyPair key = asymmetricKeyPairGenerator("RSA", 256);
      System.out.println(JSONObject.toJSONString(key));
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
