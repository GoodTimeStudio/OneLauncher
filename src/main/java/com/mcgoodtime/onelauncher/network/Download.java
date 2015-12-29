package com.mcgoodtime.onelauncher.network;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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

    public void downloadFile() throws IOException {
        downloadFile(".");
    }

    public void downloadFile(String savePath) throws IOException {
        int byteRead;
        byte[] buffer = new byte[1204];

        if (savePath.isEmpty()) {
            throw new IOException("Unknown save location");
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

    /**
     *
     * @param scale accuracy
     * @return percentage
     */
    public static double getPercentageFormLong(long num, long total, int scale) {
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
        df.setMaximumFractionDigits(scale);
        df.setRoundingMode(RoundingMode.HALF_UP);
        return num / total * 100;
    }
}