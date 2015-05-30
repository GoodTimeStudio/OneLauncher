package com.mcgoodtime.gtgames.core;

import javax.swing.*;

public class GT_Games extends JFrame {

    public static final int VERSION_ID = 1;
    public static final String VERSION = "0.1";

    protected static String latestVerName;

    public  static  void main(String[] args) {
        GetVersionInfo thread  = new GetVersionInfo();
        thread.run();

        boolean b = Update.isLatestVer();
        if (!b) {
            System.out.println("AutoUpdate:正在下载更新");
            Download.downloadFile("http://mcgoodtime.com:8080/Download/gtgames/" + "GoodTimeGames" + latestVerName + ".jar");
        }
    }

}
