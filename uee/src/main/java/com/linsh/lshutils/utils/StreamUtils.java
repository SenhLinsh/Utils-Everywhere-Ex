package com.linsh.lshutils.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/03/07
 *    desc   :
 * </pre>
 */
public class StreamUtils {

    public static String getText(InputStream inputStream) {
        return getText(inputStream, "UTF-8");
    }

    public static String getText(InputStream inputStream, String charsetName) {
        StringBuilder fileContent = new StringBuilder();
        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(inputStream, charsetName);
            reader = new BufferedReader(is);
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!fileContent.toString().equals("")) {
                    fileContent.append("\r\n");
                }
                fileContent.append(line);
            }
            return fileContent.toString();
        } catch (IOException e) {
            return null;
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
}
