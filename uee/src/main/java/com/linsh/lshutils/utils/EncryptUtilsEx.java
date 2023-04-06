package com.linsh.lshutils.utils;

import android.util.Base64;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2023/04/04
 *    desc   :
 * </pre>
 */
public class EncryptUtilsEx {

    /**
     * 使用公钥进行 RSA 加密
     *
     * @param plaintext 明文
     * @param publicKey 公钥
     * @return 已加密的 base64 编码字符串
     */
    public static String encryptRSA(String plaintext, String publicKey) throws Exception {
        // 得到公钥
        // 注：公钥数据需要提前进行处理，去头去尾去换行
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKey, Base64.NO_WRAP));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey keyPublic = kf.generatePublic(keySpec);
        // 加密数据
        // 注：Android 系统的 RSA 实现是 RSA/None/NoPadding，但标准 JDK 实现是 RSA/None/PKCS1Padding（后台）
        Cipher cp = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cp.init(Cipher.ENCRYPT_MODE, keyPublic);
        byte[] encryptedBytes = cp.doFinal(plaintext.getBytes());
        return Base64.encodeToString(encryptedBytes, Base64.NO_WRAP);
    }
}
