package com.nishchay.thread.synchronize.a01netpractice;

public class PitchAllocate implements Runnable {

    private final Pitch pitch;
    private final String playerName;

    public PitchAllocate(Pitch pitch, String playerName) {
        this.pitch = pitch;
        this.playerName = playerName;
    }

    @Override
    public void run() {
        pitch.practice(playerName);
    }

}