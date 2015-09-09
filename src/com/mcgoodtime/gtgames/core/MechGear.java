package com.mcgoodtime.gtgames.core;

import com.mcgoodtime.gtgames.PlaybackListenerBase;
import com.mcgoodtime.gtgames.ResourcesManager;
import com.mcgoodtime.gtgames.gui.MainWindow;
import com.mcgoodtime.gtgames.network.Download;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.swing.*;
import java.util.Calendar;

public class MechGear extends JFrame implements Runnable {

    public static final int VERSION_ID = 1;
    public static final String VERSION = "0.1";
    public static final String serverAddress = "play.mcgoodtime.com";

    public static AdvancedPlayer advPlayer;

    protected static String latestVerName;

    public static void main(String[] args) {
        Download.downloadFile("http://mgl.oss-cn-shenzhen.aliyuncs.com/InventoryTweaks-1.59-dev-152.jar");
        ResourcesManager.loadTexture();
        MechGear games = new MechGear();

        Thread backgroundMusicThread = new Thread() {
            @Override
            public void run() {
                try {
                    advPlayer = new AdvancedPlayer(MechGear.class.getResourceAsStream("/sound/MobileOrchestra.mp3"));
                    advPlayer.setPlayBackListener(new PlaybackListenerBase());
                    System.out.println(advPlayer.getPlayBackListener());
                    advPlayer.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundMusicThread.start();

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
