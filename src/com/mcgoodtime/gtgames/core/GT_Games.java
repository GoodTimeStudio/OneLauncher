package com.mcgoodtime.gtgames.core;

import com.mcgoodtime.gtgames.ResourcesManager;
import com.mcgoodtime.gtgames.gui.MainWindow;

import javax.swing.*;
import java.util.Calendar;

public class GT_Games extends JFrame implements Runnable {

    public static final int VERSION_ID = 1;
    public static final String VERSION = "0.1";

    protected static String latestVerName;

    public static void main(String[] args) {
        ResourcesManager.loadTexture();
        GT_Games games = new GT_Games();
        games.run();
/*
        GetVersionInfo thread  = new GetVersionInfo();
        thread.run();

        boolean b = Update.isLatestVer();
        if (!b) {
            System.out.println("AutoUpdate:正在下载更新");
            Download.downloadFile("http://mcgoodtime.com:8080/Download/gtgames/" + "GoodTimeGames" + latestVerName + ".jar");
        }*/
    }

    public static String getHelloWord() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        if(hour < 6) {
            return "凌晨";
        } else if (hour < 11) {
            return "早上好";
        } else if (hour < 13) {
            return "中午好";
        } else if (hour < 18) {
            return "下午好";
        } else if (hour < 24) {
            return "晚上好";
        } else {
            return null;
        }
    }

    @Override
    public void run() {
        MainWindow.initGui();
    }
}
