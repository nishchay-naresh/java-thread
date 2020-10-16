package com.nishchay.thread.synchronize.netpractice;

class PitchAllocate implements Runnable {

    Pitch pitch;
    String playerName;

    public PitchAllocate(Pitch pitch, String playerName) {
        this.pitch = pitch;
        this.playerName = playerName;
    }

    @Override
    public void run() {
        pitch.practice(playerName);
    }

}