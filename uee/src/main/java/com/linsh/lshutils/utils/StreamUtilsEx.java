package com.linsh.lshutils.utils;

import com.linsh.utilseverywhere.StringUtils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/03/07
 *    desc   :
 * </pre>
 */
public class StreamUtilsEx {

    /**
     * 获取流中的文本
     *
     * @param inputStream 输入流
     */
    public static String getText(InputStream inputStream) throws IOException {
        return getText(inputStream, "UTF-8");
    }

    /**
     * 获取流中的文本
     *
     * @param inputStream 输入流
     * @param charsetName 字符集
     */
    public static String getText(InputStream inputStream, String charsetName) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(inputStream, charsetName);
            reader = new BufferedReader(is);
            String line;
            while ((line = reader.readLine()) != null) {
                if (fileContent.length() == 0) {
                    fileContent.append(StringUtils.lineSeparator());
                }
                fileContent.append(line);
            }
            return fileContent.toString();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将输入流写入文件
     *
     * @param inputStream 输入流
     * @param file        文件
     * @return 是否写入成功
     */
    public static void saveToFile(InputStream inputStream, File file) throws IOException {
        if (inputStream == null)
            throw new IllegalArgumentException("inputStream == null");
        File dir = file.getParentFile();
        if (dir != null && !dir.exists()) {
            dir.mkdirs();
        }
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file, false));
            byte[] data = new byte[8192];
            int len;
            while ((len = inputStream.read(data, 0, data.length)) != -1) {
                os.write(data, 0, len);
            }
        } finally {
            try {
                inputStream.close();
            } finally {
                if (os != null) {
                    os.close();
                }
            }
        }
    }
}
