package com.nishchay.thread.synchronize.a01netpractice;

import com.nishchay.Utils;


/*
 * ========= understand synchronization + object sharing ====================
 *
 * Here sharing the same object is important in both of the Threads
 * As lock (monitor) is on the Display object, So we need to have the same object
 *   Only one thread at a time can execute wish() per Display object
 *
 * o/p=>
 *	When there are 2 different display object
 *	Good Morning ...Good Morning ...Dhoni
 *	Yuvraj
 *
 *	When the same display object been shared
 *	Good Morning ...Yuvraj
 *	Good Morning ...Dhoni
 *
 * */
public class WishingPlayers {

    public static void main(String[] args) {
        Display display = new Display();
        Thread t1 = new Thread(new WishTask(display, "Dhoni"));

         Display display1 = new Display();
        Thread t2 = new Thread(new WishTask(display1, "Yuvraj"));

        t1.start();
        t2.start();
    }

    static class Display {

        public synchronized void wish(String name) {
            System.out.print("Good Morning ...");
            Utils.sleep0(2 * 1000);
            System.out.println(name);
        }
    }

    static class WishTask implements Runnable {

        private final Display display;
        private final String name;

        public WishTask(Display display, String name) {
            this.display = display;
            this.name = name;
        }

        @Override
        public void run() {
            display.wish(name);
        }
    }
}
