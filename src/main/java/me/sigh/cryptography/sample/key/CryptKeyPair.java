package me.sigh.cryptography.sample.key;

/**
 * @Author: sigh
 * @Date: 2018-十月-18
 * @Time: 14:54
 */
public class CryptKeyPair {

  private String privateKey;
  private String publicKey;

  public String getPrivateKey() {
    return privateKey;
  }

  public void setPrivateKey(String privateKey) {
    this.privateKey = privateKey;
  }

  public String getPublicKey() {
    return publicKey;
  }

  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }
}
