package com.mcgoodtime.gtgames;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

/**
 * Created by suhao on 2015.8.26.0026.
 *
 * @author suhao
 */
public class PlaybackListenerBase extends PlaybackListener {
    private AdvancedPlayer player;

    @Override
    public void playbackStarted(PlaybackEvent playbackEvent) {
        player = playbackEvent.getSource();
    }

    @Override
    public void playbackFinished(PlaybackEvent playbackEvent) {

    }
}
