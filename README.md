# cryptography
encryption &amp; decryption etc. in Java



### Tips:
受限于JCE，加解密的密钥长度的理论范围没有在JRE中支持，具体如下：

| 算法 | 理论长度 | 实际支持长度 | 说明 |
| --- | --- | --- | --- |
|DES|值域：{56, 128}|56|JCE限制|
|AES|值域：{128, 192, 256}|128|JCE限制|
|RSA|区间： [512, 16384] |{512, 1024}|开发本地测试时发现，密钥长度超过1500的时候，会有性能上的损失，所以选择支持512和1024两种|

可以通过替换JRE中JCE(local_policy.jar, US_export_policy.jar 两个文件, 可在[Oracle官网](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html)下载) 来支持其他长度。

***需要注意的是*** : 不管明文长度是多少，RSA 生成的密文长度总是固定的。但是明文长度不能超过密钥长度。
比如 `Java 默认的 RSA 加密实现` 不允许明文长度超过密钥长度减去 11(单位是字节，也就是 byte)。也就是说，如果我们定义的密钥(我们可以通过 java.security.KeyPairGenerator.initialize(int keysize) 来定义密钥长度)长度为 1024(单位是位，也就是 bit)，生成的密钥长度就是 `1024位/8位/字节 = 128字节`，那么我们需要加密的明文长度不能超过 `128字节 - 11 字节 = 117字节`。也就是说，我们最大能将 117 字节长度的明文进行加密，否则会出问题(抛诸如 javax.crypto.IllegalBlockSizeException: Data must not be longer than 117 bytes 的异常)。而 `BC 提供的加密算法` 能够支持到的 `RSA 明文长度最长为密钥长度`。