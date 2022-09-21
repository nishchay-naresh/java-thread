package com.nishchay.concurrentpkg.pool.completionservice;

import java.util.concurrent.Callable;

public class DownloadTask implements Callable<String> {

    private final String taskName;
    private final int sleepTime;

    public DownloadTask(String taskName, int sleepTime) {
        this.taskName = taskName;
        this.sleepTime = sleepTime;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Started taskName: " + taskName);
        int size = sleepTime * 1000;
        Thread.sleep(size);
        return "Completed taskName: " + taskName + " Downloaded data - " + size;
    }
}
