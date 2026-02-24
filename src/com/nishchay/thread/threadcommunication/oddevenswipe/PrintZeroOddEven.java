package com.nishchay.thread.threadcommunication.oddevenswipe;


import java.util.concurrent.Semaphore;

/* print A, B, C in sequence using 3 threads
 * odd t - O, O, O, O
 * even t - E, E, E, E
 * zero t - 0, 0, 0, 0
 * n = 5 output => 0,O,0,E,0,O,0,E,0,O
 *
 *        for (int i = 1; i < 5; i++) {
 *            printZero();
 *            if(i%2==0){
 *                printEven();
 *            }else{
 *                printEven();
 *            }
 *        }
 *
 * */
public class PrintZeroOddEven {

    public static void main(String[] args) {
        int n = 5;
        ZeroEvenOdd sharedObject = new ZeroEvenOdd(n);
        Thread t1 = new Thread(() -> sharedObject.printZeroTask());
        Thread t2 = new Thread(() -> sharedObject.printEvenTask());
        Thread t3 = new Thread(() -> sharedObject.printOddTask());

        // Start in random order to prove correctness
        t2.start();
        t3.start();
        t1.start();
    }

    static class ZeroEvenOdd {

        private final int n;

        private final Semaphore zero = new Semaphore(1);
        private final Semaphore even = new Semaphore(0);
        private final Semaphore odd = new Semaphore(0);

        public ZeroEvenOdd(int n) {
            this.n = n;
        }

        public void printZeroTask() {
            for (int i = 1; i <= n; i++) {
                semaphoreAcquire(zero);
                System.out.println("0, ");
                if (i % 2 == 0) {
                    semaphoreRelease(even);
                } else {
                    semaphoreRelease(odd);
                }
            }
        }

        public void printOddTask() {
            for (int i = 1; i <= n; i += 2) {
                semaphoreAcquire(odd);
                System.out.println(i + ", ");
                semaphoreRelease(zero);
            }
        }

        public void printEvenTask() {
            for (int i = 2; i <= n; i += 2) {
                semaphoreAcquire(even);
                System.out.println(i + ", ");
                semaphoreRelease(zero);
            }
        }

        public void semaphoreAcquire(Semaphore semaphore) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        public void semaphoreRelease(Semaphore semaphore) {
            semaphore.release();
        }
    }
}
