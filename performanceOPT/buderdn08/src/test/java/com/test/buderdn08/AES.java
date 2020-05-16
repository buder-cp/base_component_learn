package com.test.buderdn08;

import org.junit.Test;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    public static String ALGORITHM = "AES";

    public static byte[] encrypt(String content, String password) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
        //用用户密码作为随机数初始化
        kgen.init(128, new SecureRandom(password.getBytes()));
        //得到一个密钥
        SecretKey secretKey = kgen.generateKey();
        //对钥密进行基本的编码
        byte[] enCodeFormat = secretKey.getEncoded();
        //转换成AES专用的密钥
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM);
        //创建一个密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM);


        byte[] byteContent = content.getBytes();
        //开始加密了
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] result = cipher.doFinal(byteContent);

        return result;

    }

    public static byte[] decrypt(byte[] content, String password) throws Exception {
        //创建AES的key生产者
        KeyGenerator kgen = KeyGenerator.getInstance(ALGORITHM);
        //利用用户密码作为随机数初始化
        kgen.init(128, new SecureRandom(password.getBytes()));
        //根据用户密码，生成一个密钥  (所有对称算法通用的)
        SecretKey secretKey = kgen.generateKey();
        //对密钥进行基本的编码
        byte[] enCodeFormat = secretKey.getEncoded();
        //转换成AES专用的密钥 RoundKey
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, ALGORITHM);
        //创建一个密码器
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        //解密
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] result = cipher.doFinal(content);
        return result;
    }

    @Test
    public void test() throws Exception {
        String content = "buder666";
        String password = "123";

        byte[] encryptByte = encrypt(content, password);
        System.out.println("加密的数据：" + new String(encryptByte));

        byte[] decrypt = decrypt(encryptByte, password);
        System.out.println("解密后的效果:" + new String(decrypt));

    }

}
