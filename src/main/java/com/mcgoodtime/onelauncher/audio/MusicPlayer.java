package com.mcgoodtime.onelauncher.audio;

import com.mcgoodtime.onelauncher.ResourcesManager;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suhao on 2015.10.3.0003.
 *
 * @author suhao
 */
public class MusicPlayer implements Runnable {

    public static List<String> musicList = new ArrayList<>();

    private Player player;
    private int index;

    public void initMusicList() {
        musicList.add("GoodTime.mp3");
        musicList.add("外婆桥.mp3");
        musicList.add("梦之雨.mp3");
    }

    public void play(int index) throws JavaLayerException {
        this.index = index;
        player = new Player(ResourcesManager.getMusicSteam(musicList.get(index)));
        player.play();
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (index == musicList.size()) {
                    index = 0;
                }
                play(index);
                index++;
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        player.close();
    }
}
