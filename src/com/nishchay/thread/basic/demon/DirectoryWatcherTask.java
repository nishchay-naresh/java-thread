package com.nishchay.thread.basic.demon;

import com.nishchay.Utils;

public class DirectoryWatcherTask implements Runnable {

    @Override
    public void run() {

        while (true) {
            System.out.println("checking directory for file update ...");

//            Utils.wait0(1 * 1000);

        }
    }
}