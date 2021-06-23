package com.nishchay.thread.basic.demon;

public class DirectoryWatcherTask implements Runnable {

    @Override
    public void run() {

        while (true) {
            System.out.println("checking directory for file update ...");

/*           try {
               Thread.sleep(1 * 1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
*/

        }
    }
}