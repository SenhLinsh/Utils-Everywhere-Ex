package com.linsh.lshutils.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2019/10/17
 *    desc   :
 * </pre>
 */
public class FileEncryptUtilsEx {

    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String AES = "AES";


    public static boolean saveEncryptedFile(byte[] rawKey, String content, File file) {
        if (rawKey == null || content == null) {
            return false;
        }
        OutputStreamWriter writer = null;
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(rawKey, AES);
            Cipher cipher = Cipher.getInstance(AES);
            //加密模式
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            FileOutputStream fos = new FileOutputStream(file, false);
            CipherOutputStream cos = new CipherOutputStream(fos, cipher);
            writer = new OutputStreamWriter(cos, DEFAULT_CHARSET);
            //使用加密流来加密
            writer.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String readEncrytedFile(byte[] rawKey, File file) {
        if (rawKey == null || file == null || !file.exists()) {
            return null;
        }

        StringBuilder fileContent = new StringBuilder();
        BufferedReader reader = null;
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(rawKey, AES);
            Cipher cipher = Cipher.getInstance(AES);
            //加密模式
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            InputStreamReader is = new InputStreamReader(
                    new CipherInputStream(new FileInputStream(file), cipher), DEFAULT_CHARSET);
            reader = new BufferedReader(is);
            String line;
            while ((line = reader.readLine()) != null) {
                if (fileContent.length() != 0) {
                    fileContent.append("\r\n");
                }
                fileContent.append(line);
            }
            return fileContent.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * AES算法加密文件
     *
     * @param rawKey   AES密钥
     * @param fromFile 要加密的文件
     * @param toFile   加密后文件
     */
    public static void encryptFile(byte[] rawKey, File fromFile, File toFile) throws Exception {
        if (!fromFile.exists()) {
            throw new NullPointerException("文件不存在");
        }
        if (toFile.exists()) {
            toFile.delete();
        }
        SecretKeySpec skeySpec = new SecretKeySpec(rawKey, AES);
        Cipher cipher = Cipher.getInstance(AES);
        //加密模式
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        FileInputStream fis = new FileInputStream(fromFile);
        FileOutputStream fos = new FileOutputStream(toFile, true);
        byte[] buffer = new byte[512 * 1024 - 16];
        int offset;
        //使用加密流来加密
        CipherInputStream bis = new CipherInputStream(fis, cipher);
        while ((offset = bis.read(buffer)) != -1) {
            fos.write(buffer, 0, offset);
            fos.flush();
        }
        fos.close();
        fis.close();
    }

    /**
     * AES算法解密文件
     *
     * @param rawKey   AES密钥
     * @param fromFile 被加密的文件
     * @param toFile   解密后文件
     */
    public static void decryptFile(byte[] rawKey, File fromFile, File toFile) throws Exception {
        if (!fromFile.exists()) {
            throw new NullPointerException("文件不存在");
        }
        if (toFile.exists()) {
            toFile.delete();
        }
        SecretKeySpec skeySpec = new SecretKeySpec(rawKey, AES);
        Cipher cipher = Cipher.getInstance(AES);
        //解密模式
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);

        FileInputStream fis = new FileInputStream(fromFile);
        FileOutputStream fos = new FileOutputStream(toFile, true);
        byte[] buffer = new byte[512 * 1024 + 16];
        int offset;
        //使用解密流来解密
        CipherInputStream cipherInputStream = new CipherInputStream(fis, cipher);
        while ((offset = cipherInputStream.read(buffer)) != -1) {
            fos.write(buffer, 0, offset);
            fos.flush();
        }
        fos.close();
        fis.close();
    }
}
