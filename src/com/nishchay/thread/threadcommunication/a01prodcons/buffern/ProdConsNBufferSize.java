package com.nishchay.thread.threadcommunication.a01prodcons.buffern;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * 	Producer–Consumer Problem
 * 	The Producer–Consumer problem is a synchronization problem where:
 * 		-	Producer thread(s) → produce data
 * 		-	Consumer thread(s) → consume data
 * 		-	Both share a bounded buffer (queue)
 *
 * 	The core challenge, One must ensure:
 * 		-	Producer does not produce when buffer is full
 * 		-	Consumer does not consume when buffer is empty
 * 		-	No race condition
 * 		-	No busy-waiting
 *
 *
 * 	Correct solution approach, Use:
 * 		-	synchronized
 * 		-	wait()
 * 		-	notify() / notifyAll()
 * 	Golden rules
 * 		-	Always call wait() inside a while
 * 		-	Call wait/notify inside synchronized block
 * 		-	Shared buffer must be thread-safe - Don't use ArrayList
 *
 * Solving a Producer–Consumer problem for dingle buzzer size using Queue
 * */
public class ProdConsNBufferSize {

    public static final int CAPACITY = 5;

    public static void main(String[] args) {

        // execute below code by commenting one after another
        bufferALImpl();
        bufferLLImpl();
    }

    private static void bufferALImpl() {
        List<String> list = IntStream.rangeClosed(0, 10)
                .mapToObj(e -> "hi - " + e)
                .collect(Collectors.toList());

        Buffer<String> buffer = new BufferALImpl<>(5);
        new Thread(() -> produceTask(buffer, list), "Producer Thread").start();
        new Thread(() -> consumeTask(buffer), "Consumer Thread").start();
    }

    private static void bufferLLImpl() {
        List<String> list = IntStream.rangeClosed(0, 10)
                .mapToObj(e -> "hi - " + e)
                .collect(Collectors.toList());

        Buffer<String> buffer = new BufferLLImpl<>(5);
        new Thread(() -> produceTask(buffer, list), "Producer Thread").start();
        new Thread(() -> consumeTask(buffer), "Consumer Thread").start();
    }

    /*
     * instead of creating a new class for thread implementation, putting thread logic in a method
     * later invoking these methods thought threads created using lambdas
     * */
    public static <T> void produceTask(Buffer<T> sharedObject, List<T> items) {

        for (int i = 1; i <= CAPACITY; i++) {
            T data = items.get(i);
            sharedObject.produce(data);
            System.out.println(Thread.currentThread().getName() + " produces:" + data);
        }
    }

    public static <T> void consumeTask(Buffer<T> sharedObject) {
        T value;
        for (int i = 1; i <= CAPACITY; i++) {
            value = sharedObject.consume();
            System.out.println(Thread.currentThread().getName() + " consume:" + value);
        }
    }

}