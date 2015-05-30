package com.mcgoodtime.gtgames.core;

import org.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by 豪 on 2015-04-06-0006.
 */
public class Update {

    protected static String updateInfo;

    /*  解析从服务器获取到的更新信息 */
    public static boolean isLatestVer() {

        JSONObject jsonObject = new JSONObject(updateInfo);
        boolean b;
        //获取jsonObject中的ver
        int ver = jsonObject.getInt("ver");
        //判断最新版本是否大于目前版本
        b = ver > GT_Games.VERSION_ID;

        if (b) {
            String verName = jsonObject.getString("verName");
            GT_Games.latestVerName = verName;
            System.out.println("AutoUpdate:检测到有新版本,版本号"+verName);
            return false;
        } else {
            System.out.println("AutoUpdate:目前已是最新版本");
            return true;
        }
    }

    public static void DownloadUpdate(){

    }
}

class GetVersionInfo extends Thread {

    @Override
    public void run() {
        try {

            URL url = new URL("http://mcgoodtime.com:8080/UpdateNotes/gtgames_latest.json");
            URLConnection connection = url.openConnection();
            InputStream in = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);

            StringBuilder stringBuiler = new StringBuilder();
            String line = null ;

            while( (line = br.readLine())!= null ){
                stringBuiler.append(line);
            }
            Update.updateInfo = stringBuiler.toString();

            br.close();
            isr.close();
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

