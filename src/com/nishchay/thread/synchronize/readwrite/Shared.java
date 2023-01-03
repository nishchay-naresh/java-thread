package com.nishchay.thread.synchronize.readwrite;

import static com.nishchay.Utils.sleep0;

public class Shared {

    private int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public synchronized void incr() {

        // read the value of x.
        int y = getX();

        // Increment the value
        y++;

        // Just assume if control is switched to
        // some other thread and it too looks at
        // the old value of x and proceeds with
        // modification. Such scenarios lead to
        // in consistent results.
        // To simulate such scenarios lets us just
        // pass the control to some other thread.

        // with sleep this thread will go to blocked state
        // for the given time interval, hence other thread
        // will get a chance.
        sleep0(1);

        // set x to new value.
        setX(y);
    }
}
