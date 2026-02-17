package com.nishchay.concurrentpkg.synchronizers.exchanger;

import java.util.concurrent.Exchanger;

/*
 * Exchanger = Two threads swap data at a meeting point
 * Exchanger in Java is a synchronization utility that allows two threads to exchange objects safely.
 * Thread1 -- (java) -->
 *                           Exchanger.exchange()
 *                                               <-- (python) -- Thread2
 *
 * Common meeting point - both thread1, thread2 execute -  Exchanger.exchange()
 * Thread1 -- (python) -->
 * Thread2 -- (java) -->
 *
 * What Happens Internally?
 * 	-	Thread1 calls exchange() → waits
 * 	-	Thread2 calls exchange() → swap happens
 * 	-	Both threads resume with exchanged value
 * 	-	If only one thread calls exchange → it blocks indefinitely.
 *
 * */
public class ExchangerEx {

    public static void main(String[] args) {

        Exchanger<String> exchanger = new Exchanger<>();

        Thread t1 = new Thread(() -> {
            try {
                String data = "java";
                System.out.println("Thread1 sending: " + data);

                String received = exchanger.exchange(data);

                System.out.println("Thread1 received: " + received);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                String data = "python";
                System.out.println("Thread2 sending: " + data);

                String received = exchanger.exchange(data);

                System.out.println("Thread2 received: " + received);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        t1.start();
        t2.start();
    }
}

/*
 * o/p =>
 * Thread1 sending: java
 * Thread2 sending: python
 * Thread1 received: python
 * Thread2 received: java
 *
 * */