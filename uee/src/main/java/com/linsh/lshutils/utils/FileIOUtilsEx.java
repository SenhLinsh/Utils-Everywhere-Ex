package com.linsh.lshutils.utils;

import com.linsh.utilseverywhere.ExceptionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2022/09/19
 *    desc   :
 * </pre>
 */
public class FileIOUtilsEx {

    public static String readFirstLine(File file) throws IOException {
        ExceptionUtils.checkNotNull(file);
        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            reader = new BufferedReader(is);
            String line;
            if ((line = reader.readLine()) != null) {
                return line;
            }
            return "";
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
