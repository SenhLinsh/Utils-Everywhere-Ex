package com.linsh.lshutils.utils;

import android.support.annotation.WorkerThread;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Set;

import javax.net.ssl.X509TrustManager;

/**
 * <pre>
 *    author : Senh Linsh
 *    github : https://github.com/SenhLinsh
 *    date   : 2018/03/07
 *    desc   : 简单 Http 连接
 * </pre>
 */
public class HttpConnectionUtils {

    @WorkerThread
    public static String get(String path) throws IOException {
        return get(path, null, null);
    }

    public static String get(String path, HashMap<String, Object> params) throws IOException {
        return get(path, params, null);
    }

    @WorkerThread
    public static String get(String path, HashMap<String, Object> params, HashMap<String, Object> headers) throws IOException {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(addParams(path, params));
            conn = (HttpURLConnection) url.openConnection();
            // 请求配置
            conn.setRequestMethod("POST"); // POST 请求
            conn.setConnectTimeout(5000); // 连接超时
            conn.setReadTimeout(5000); // 读取超时
            conn.setRequestProperty("Accept-Charset", "utf-8"); // 设置编码
            conn.setUseCaches(false); // 不使用缓存
            // 添加 Header
            if (headers != null) {
                for (String key : headers.keySet()) {
                    conn.setRequestProperty(key, headers.get(key).toString());
                }
            }
            conn.connect();
            // 获取响应码
            if (conn.getResponseCode() == 200) {
                // 读取流
                return StreamUtils.getText(conn.getInputStream());
            } else {
                throw new ConnectException(conn.getResponseMessage() + ". Response code is " + conn.getResponseCode());
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    @WorkerThread
    public static String post(String path) throws IOException {
        return post(path, null, null);
    }

    @WorkerThread
    public static String post(String path, HashMap<String, Object> params, HashMap<String, Object> headers) throws IOException {
        return post(path, params, headers, (String) null);
    }

    @WorkerThread
    public static String post(String path, HashMap<String, Object> params, HashMap<String, Object> headers, HashMap<String, Object> bodies) throws IOException {
        return post(path, params, headers, formatBody(bodies));
    }

    @WorkerThread
    public static String post(String path, HashMap<String, Object> params, HashMap<String, Object> headers, String body) throws IOException {
        HttpURLConnection conn = null;
        OutputStreamWriter outputStreamWriter = null;
        // 是否有http正文提交
        boolean hasBody = body != null && body.length() > 0;
        try {
            URL url = new URL(addParams(path, params));
            conn = (HttpURLConnection) url.openConnection();
            // 请求配置
            conn.setRequestMethod("POST"); // POST 请求
            conn.setConnectTimeout(5000); // 连接超时
            conn.setReadTimeout(5000); // 读取超时
            conn.setRequestProperty("Accept-Charset", "utf-8"); // 设置编码
            conn.setUseCaches(false); // 不使用缓存
            // 添加 Body 长度
            if (hasBody) {
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Length", String.valueOf(body.length()));
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            }
            // 添加 Header
            if (headers != null) {
                for (String key : headers.keySet()) {
                    conn.setRequestProperty(key, headers.get(key).toString());
                }
            }
            conn.connect();
            // 发送 Body
            if (hasBody) {
                outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
                outputStreamWriter.write(body);
                outputStreamWriter.flush();
            }
            // 获取响应码
            if (conn.getResponseCode() == 200) {
                // 读取流
                return StreamUtils.getText(conn.getInputStream());
            } else {
                throw new ConnectException(conn.getResponseMessage() + ". Response code is " + conn.getResponseCode());
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String addParams(String url, HashMap<String, Object> params) {
        if (params == null || params.size() == 0) return url;
        StringBuilder builder = new StringBuilder(url);
        Set<String> keySet = params.keySet();
        int i = 0;
        for (String key : keySet) {
            if (i == 0 && !url.contains("?"))
                builder.append("?").append(key).append("=").append(URLEncoder.encode(params.get(key).toString()));
            else
                builder.append("&").append(key).append("=").append(URLEncoder.encode(params.get(key).toString()));
            i++;
        }
        return builder.toString();
    }

    private static String formatBody(HashMap<String, Object> bodies) {
        if (bodies == null || bodies.size() == 0) return null;
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (String key : bodies.keySet()) {
            if (i == 0) builder.append(key).append("=").append(bodies.get(key));
            else builder.append("&").append(key).append("=").append(bodies.get(key));
            i++;
        }
        return builder.toString();
    }

    /**
     * 证书信任管理器（用于https请求）
     * 这个证书管理器的作用就是让它信任我们指定的证书，下面的代码意味着信任所有证书，不管是否权威机构颁发。
     *
     * @author zhangwenchao
     */
    public static class MyX509TrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {


        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {


        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {

            return null;
        }


    }
}
