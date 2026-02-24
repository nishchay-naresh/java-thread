package com.nishchay.thread.threadcommunication.a03nthreads;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/*
 * Sum 1-100 recursively among many threads using ForkJoinPool
 * Let’s sum numbers from 1 to N:
 *
 * */
public class ZSumTill100ByForkJoinPool {

    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool();
        int result = pool.invoke(new SumTask(1, 100));
        System.out.println(result);
    }

    /*
     * What Happens Internally?
     *	    Task splits recursively
     *	    Small tasks executed in parallel
     *	    Threads steal tasks if idle
     *	    Results combined via join()
     *
     * 	SumTask(1, 100)
     * 	It becomes:
     *
     * 	(1–100)
     * 	   |
     * 	(1–50) + (51–100)
     * 	   |
     * 	(1–25) + (26–50) + ...
     *
     * 	It keeps splitting until range ≤ 10.
     * 	Then small sums are calculated and combined upward.
     *
     * */
    static class SumTask extends RecursiveTask<Integer> {

        private final int start;
        private final int end;

        SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        // ForkJoinPool calls compute() when executing the task.
        protected Integer compute() {

            // Base Condition (Small Task) - If range is small enough: Calculate normally, No splitting here.
            if (end - start <= 10) {
                int sum = 0;
                for (int i = start; i <= end; i++)
                    sum += i;
                return sum;
            }

            // Recursive Case (Large Task)
            int mid = (start + end) / 2;

            SumTask left = new SumTask(start, mid);
            SumTask right = new SumTask(mid + 1, end);

            ForkJoinTask<Integer> leftResult = left.fork();     // fork left task
            ForkJoinTask<Integer> rightResult = right.fork();   // fork right task

            // wait for left + wait for right
            return leftResult.join() + rightResult.join();
        }
    }
}