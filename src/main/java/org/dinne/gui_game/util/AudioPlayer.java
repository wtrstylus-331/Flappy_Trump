package org.dinne.gui_game.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioPlayer {
    private MediaPlayer mediaPlayer;
    private Media currentMedia;

    public AudioPlayer(Media input) {
        this.currentMedia = input;

        if (this.currentMedia != null) {
            this.mediaPlayer = new MediaPlayer(this.currentMedia);
        }
    }

    public void setCycle(int i) {
        if (isValid()) {
            this.mediaPlayer.setCycleCount(i);
        }
    }

    public void play() {
        if (this.currentMedia != null) {
            this.mediaPlayer = new MediaPlayer(this.currentMedia);
        }

        this.mediaPlayer.play();
    }

    public void setVolume(double volume) {
        this.mediaPlayer.setVolume(volume);
    }

    public void setMedia(Media media) {
        this.currentMedia = media;

        if (this.currentMedia != null) {
            this.mediaPlayer = new MediaPlayer(this.currentMedia);
        }
    }

    public Media getMedia() {
        if (isValid()) {
            return this.currentMedia;
        }
        return null;
    }

    private boolean isValid() {
        return this.mediaPlayer != null;
    }
}
