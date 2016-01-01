package com.mcgoodtime.onelauncher.core;

import com.mcgoodtime.onelauncher.ResourcesManager;
import com.mcgoodtime.onelauncher.audio.MusicPlayer;
import com.mcgoodtime.onelauncher.gui.MainWindow;
import javazoom.jl.player.advanced.AdvancedPlayer;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class OneLauncher extends JFrame {

    public static final int VERSION_ID = 1;
    public static final String VERSION = "0.1";
    public static final String serverAddress = "play.mcgoodtime.com";

    public static AdvancedPlayer advPlayer;

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>(Arrays.asList(args));
        for (int i = 0; i < list.size(); i++) {
           switch (list.get(i)) {
               case "-update": {
                   File oldFile = new File(list.get(i + 1));
                   if (!oldFile.delete()) {
                       System.out.println("Failed to delete old versions, attempt to remove it again" +
                               " when the program will be shut down, or try to manually delete");
                       oldFile.deleteOnExit();
                       System.out.println("Update complete");
                   }

               }
           }
        }

        //new Thread(new Update()).start();
        new Thread(new ResourcesManager()).start();

        MusicPlayer player = new MusicPlayer();
        player.initMusicList();
        new Thread(player).start();

        MainWindow.initGui();
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

}
