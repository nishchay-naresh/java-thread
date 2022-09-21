package com.nishchay.concurrentpkg.pool.completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorCompletionServiceExample {

    public static void main(String[] args) {

        executorServiceEvaluation();
        executorCompletionServiceEvaluation();

    }

    /*
     *
     *	Problem -
     *	With the case ExecutorService, since we are extracting out the result (future.get()) in order of their submission.
     *	So if the last submitted task is finished with the execution and the first one is still in execution.
     *	So we have to wait for first task to finish to extract the result of finished last job.
     *
     * Here we are extracting the result in the order of their submission
     * */
    private static void executorServiceEvaluation() {

        List<DownloadTask> downloadTasks = getTaskList();

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        List<Future<String>> futures = new ArrayList<>();

        for (DownloadTask currTask : downloadTasks) {
            Future<String> future = executorService.submit(currTask);
            futures.add(future);
        }

        executorService.shutdown();

        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    /*
     *
     *	CompletionsService(I)  <-  ExecutorCompletionsService class is there to rescue us
     *	It takes the same ExecutorService object for the execution of submitted task,
     *	But uses an addition Queue<Future>, which arranges/holds the task upon completion of their execution.
     *
     * */
    private static void executorCompletionServiceEvaluation() {

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


    private static List<DownloadTask> getTaskList() {
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
