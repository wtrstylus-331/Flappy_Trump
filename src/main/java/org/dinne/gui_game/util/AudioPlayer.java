/*
Program Name: AudioPlayer.java
Author: Saisrikara Dinne
Date: Dec 19, 2024
Purpose: Class meant to serve as an AudioPlayer object
to play audio for certain scenarios, background music and sound effects
 */

package org.dinne.gui_game.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioPlayer {
    // Private variables to store MediaPlayer and Media to be played
    private MediaPlayer mediaPlayer;
    private Media currentMedia;

    // Constructor with Media input, create a new object if Media is not null
    public AudioPlayer(Media input) {
        this.currentMedia = input;

        if (this.currentMedia != null) {
            this.mediaPlayer = new MediaPlayer(this.currentMedia);
        }
    }

    // Method to set cycle of MediaPlayer object, i.e. repeat
    public void setCycle(int i) {
        if (isValid()) {
            this.mediaPlayer.setCycleCount(i);
        }
    }

    // Method to play MediaPlayer, create a new object if null
    public void play() {
        if (this.currentMedia != null) {
            this.mediaPlayer = new MediaPlayer(this.currentMedia);
        }

        this.mediaPlayer.play();
    }

    // Method to set volume of MediaPlayer
    public void setVolume(double volume) {
        this.mediaPlayer.setVolume(volume);
    }

    // Method to set current Media to be played
    public void setMedia(Media media) {
        this.currentMedia = media;

        if (this.currentMedia != null) {
            this.mediaPlayer = new MediaPlayer(this.currentMedia);
        }
    }

    // Method to return current Media stored
    public Media getMedia() {
        if (isValid()) {
            return this.currentMedia;
        }
        return null;
    }

    // Method to return if current MediaPlayer object exists
    private boolean isValid() {
        return this.mediaPlayer != null;
    }
} // end of AudioPlayer class