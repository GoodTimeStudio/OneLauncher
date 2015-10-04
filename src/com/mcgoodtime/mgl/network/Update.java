package com.mcgoodtime.mgl.network;

import com.mcgoodtime.mgl.core.MechGear;
import org.json.JSONObject;

import java.io.*;
import java.net.*;

/**
 * Created by 豪 on 2015-04-06-0006.
 *
 * @author suhao
 */
public class Update implements Runnable {

    private String newVer;
    private String newVerFile;

    public boolean isLatestVer() throws IOException {
        JSONObject jsonObject = new JSONObject(getVersionInfo());
        boolean b;

        int ver = jsonObject.getInt("verID");
        b = ver > MechGear.VERSION_ID;

        if (b) {
            newVer = jsonObject.getString("verName");
            System.out.println("AutoUpdate:检测到有新版本,版本号"+ newVer);
            return false;
        } else {
            System.out.println("AutoUpdate:目前已是最新版本");
            return true;
        }
    }

    public void downloadUpdate() throws IOException {
        String file = "mgl-" + newVer + ".jar";
        this.newVerFile = file;
        URL url = new URL("http://mcgoodtime.com/mgl/" + file);
        URL url2 = new URL("https://minecraft-goodtime.github.io/mgl/" + file);

        Download downloader;
        InputStream in = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(1500);
            in = connection.getInputStream();
            downloader = new Download(url.toString());
        } catch (Exception e) {
            System.out.println("Failed to connect to mgl download server, try again...");
            HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
            connection2.setConnectTimeout(1500);
            in = connection2.getInputStream();
            downloader = new Download(url2.toString());
        }

        downloader.downloadFile();
    }

    private String getVersionInfo() throws IOException {
        URL url = new URL("http://mcgoodtime.com/mgl/mgl.json");
        URL url2 = new URL("https://minecraft-goodtime.github.io/mgl/mgl.json");

        InputStream in = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(1500);
            in = connection.getInputStream();
        } catch (Exception e) {
            System.out.println("Failed to connect to server, try again...");
            HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
            connection2.setConnectTimeout(1500);
            in = connection2.getInputStream();
        }

        try {
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while( (line = br.readLine())!= null ){
                stringBuilder.append(line);
            }

            br.close();
            isr.close();
            in.close();

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update() {
        URL url = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        String fileName;
        try {
            fileName = URLDecoder.decode(url.getPath(), "utf-8");
            if (fileName.endsWith(".jar")) {
                fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
            }
            File file = new File(fileName);
            file.deleteOnExit();
            System.out.println(fileName);
            System.out.println("正在重新启动...");
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("java -jar " + newVerFile + " --update");
            System.out.println("AvailableProcessors: " + runtime.availableProcessors());
            runtime.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {

            if (!this.isLatestVer()) {
                System.out.println("开始下载更新");
                this.downloadUpdate();
                System.out.println("下载完成，正在更新...");
                this.update();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

