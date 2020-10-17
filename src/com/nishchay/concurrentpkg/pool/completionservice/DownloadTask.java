package com.nishchay.concurrentpkg.pool.completionservice;

import java.util.concurrent.Callable;

public class DownloadTask implements Callable<String> {

    private String taskName;
    private int sleepTime;


    public DownloadTask(String taskName, int sleepTime) {
        this.taskName = taskName;
        this.sleepTime = sleepTime;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Started taskName: " + taskName);
        int size = sleepTime * 1000;
        Thread.sleep(size);
        String res = "Completed taskName: " + taskName + " Downloaded data - " + size;
        return res;
    }
}
