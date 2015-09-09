package com.mcgoodtime.gtgames.network;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Download {
    public static void downloadFile(String downloadURL) throws IOException {
        int bytes = 0;
        int byteread = 0;

        URL url = new URL(downloadURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");

        InputStream inStream = connection.getInputStream();
        FileOutputStream fs = new FileOutputStream("c:/abc.gif");

        byte[] buffer = new byte[1204];
        int length;
        while ((byteread = inStream.read(buffer)) != -1) {
            bytes += byteread;
            fs.write(buffer, 0, byteread);
        }
    }

    //  获得文件长度
    public static long getFileSize(String sURL) {
        int nFileLength = -1;
        try {
            URL url = new URL(sURL);
            HttpURLConnection httpConnection = (HttpURLConnection) url
                    .openConnection();
            httpConnection.setRequestProperty("User-Agent", "Internet Explorer");

            int responseCode = httpConnection.getResponseCode();
            if (responseCode >= 400) {
                System.err.println("Error Code : " + responseCode);
                return -2; // -2 represent access is error
            }
            String sHeader;
            for (int i = 1;; i++) {
                sHeader = httpConnection.getHeaderFieldKey(i);
                if (sHeader != null) {
                    if (sHeader.equals("Content-Length")) {
                        nFileLength = Integer.parseInt(httpConnection
                                .getHeaderField(sHeader));
                        break;
                    }
                } else
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("AutoUpdate:FileLength:"+nFileLength);
        return nFileLength;
    }

    public void downloadFormOSS() throws IOException {
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(5000);
        conf.setMaxErrorRetry(3);
        conf.setSocketTimeout(2000);

        OSSClient client = new OSSClient("https://mgl.oss-cn-shenzhen.aliyuncs.com", "oah6WPrpdnfnPlP9", "kLPqNfbTwEXuwMomHasN9EoZI1Ou9m");
        OSSObject object = client.getObject("mgl", "oah6WPrpdnfnPlP9");
        InputStream in = object.getObjectContent();
        byte[] bytes = new byte[1024];
        int i;
        while ((i = in.read(bytes, 0, 1024)) > 0) {

        }
    }
}