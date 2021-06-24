package com.nishchay.thread.synchronize.netpractice;

public class PitchAllocate implements Runnable {

    private Pitch pitch;
    private String playerName;

    public PitchAllocate(Pitch pitch, String playerName) {
        this.pitch = pitch;
        this.playerName = playerName;
    }

    @Override
    public void run() {
        pitch.practice(playerName);
    }

}