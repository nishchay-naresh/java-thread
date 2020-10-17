package com.nishchay.concurrentpkg.pool.completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorCompletionServiceExample {

    public static void main(String[] args) {

        ExecutorCompletionServiceExample ref = new ExecutorCompletionServiceExample();
        ref.executorServiceEvaluation();
        ref.executorCompletionServiceEvaluation();

    }

    public void executorServiceEvaluation() {

        List<DownloadTask> downloadTasks = getTaskList();

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        List<Future<String>> futures = new ArrayList<>();

        for (DownloadTask currTask : downloadTasks) {
            Future<String> future = executorService.submit(currTask);
            futures.add(future);
        }

        executorService.shutdown();

        for (Future future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    public void executorCompletionServiceEvaluation() {

        List<DownloadTask> downloadTasks = getTaskList();

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletionService<String> executorCompletionService = new ExecutorCompletionService<>(executorService);

        for (DownloadTask currTask : downloadTasks) {
            executorCompletionService.submit(currTask);
        }

        executorService.shutdown();

        for (int i = 0; i < downloadTasks.size(); i++) {
            try {
                String result = executorCompletionService.take().get();
                System.out.println(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }


    }


    public List<DownloadTask> getTaskList() {
        List<DownloadTask> downloadTasks = new ArrayList<>();
        downloadTasks.add(new DownloadTask("Task 1", 8));
        downloadTasks.add(new DownloadTask("Task 2", 5));
        downloadTasks.add(new DownloadTask("Task 3", 2));
        downloadTasks.add(new DownloadTask("Task 4", 1));
        downloadTasks.add(new DownloadTask("Task 5", 1));
        return downloadTasks;
    }


}

/*
O/P =>

        Completed taskName: Task 1 Downloaded data - 8000
        Completed taskName: Task 2 Downloaded data - 5000
        Completed taskName: Task 3 Downloaded data - 2000
        Completed taskName: Task 4 Downloaded data - 1000
        Completed taskName: Task 5 Downloaded data - 1000

        Completed taskName: Task 4 Downloaded data - 1000
        Completed taskName: Task 3 Downloaded data - 2000
        Completed taskName: Task 5 Downloaded data - 1000
        Completed taskName: Task 2 Downloaded data - 5000
        Completed taskName: Task 1 Downloaded data - 8000
*/
