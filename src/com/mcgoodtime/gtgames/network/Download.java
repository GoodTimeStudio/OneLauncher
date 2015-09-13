package com.mcgoodtime.gtgames.network;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Download {

    private HttpURLConnection connection;

    private String fileName;
    private URL downloadURL;
    private long fileLength;

    public Download(String downloadURL) throws IOException {
        this.downloadURL = new URL(downloadURL);
        this.fileName = downloadURL.substring(downloadURL.lastIndexOf("/") + 1);
        this.connection = (HttpURLConnection) this.downloadURL.openConnection();
        this.connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");

        for (int i = 1;; i++) {
            String header = this.connection.getHeaderFieldKey(i);
            if (header != null) {
                if (header.equals("Content-Length")) {
                    this.fileLength = Integer.parseInt(connection.getHeaderField(header));
                    break;
                }
            }
        }
    }

    public void downloadFile(String savePath) throws IOException {
        int byteRead;
        byte[] buffer = new byte[1204];

        if (savePath.isEmpty()) {
            savePath = ".";
        }

        InputStream inStream = connection.getInputStream();
        FileOutputStream fs = new FileOutputStream(savePath + "/" + fileName);

        while ((byteRead = inStream.read(buffer)) != -1) {
            fs.write(buffer, 0, byteRead);
        }
    }

    public String getFileName() {
        return fileName;
    }

    public URL getDownloadURL() {
        return downloadURL;
    }

    public long getFileLength() {
        return fileLength;
    }

    public static class OSSDownload {

        private final String BUCKET_NAME = "mgl";
        private String file;

        private OSSClient client;
        private long fileLength;

        public OSSDownload(String file) {
            client = new OSSClient("https://oss-cn-shenzhen.aliyuncs.com", "oah6WPrpdnfnPlP9", "kLPqNfbTwEXuwMomHasN9EoZI1Ou9m");
            this.fileLength = client.getObjectMetadata(BUCKET_NAME, file).getContentLength();
            this.file = file;
        }

        public void downloadFormOSS(String savePath) throws IOException {
            GetObjectRequest request = new GetObjectRequest(BUCKET_NAME, this.file);
            if (savePath.isEmpty()) {
                savePath = ".";
            }
            client.getObject(request, new File(savePath + "/" + this.file));
        }

        public long getFileLength() {
            return this.fileLength;
        }
    }
}