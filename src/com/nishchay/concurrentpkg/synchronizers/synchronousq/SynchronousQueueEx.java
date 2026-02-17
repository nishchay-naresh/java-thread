package com.nishchay.concurrentpkg.synchronizers.synchronousq;

import java.util.concurrent.SynchronousQueue;

/*
 * SynchronousQueue is a special BlockingQueue in Java where:
 * 	    -   Every put() must wait for a take()
 * 	    -   Every take() must wait for a put()
 * 	    -   It has zero capacity. No elements are stored internally.
 *      -   It acts as a direct handoff mechanism between two threads.
 *
 *
 * What Happens Internally?
 *
 *	 	When producer calls:  queue.put("Hello");
 *	 	If no consumer is waiting
 *	 		producer blocks
 *	 	Else
 *	 		Data is transferred directly And Both threads continue
 *
 *	 	When consumer calls: queue.take();
 *	 	If no producer is waiting:
 *	 		consumer blocks
 *	 	Else
 *	 		Data is transferred directly And Both threads continue
 *
 * */
public class SynchronousQueueEx {

    public static void main(String[] args) {

        SynchronousQueue<String> queue = new SynchronousQueue<>();

        Thread producer = new Thread(() -> {
            try {
                System.out.println("Producer: Putting item...");
                queue.put("Hello");
                System.out.println("Producer: Item handed off.");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                Thread.sleep(2000);  // simulate delay
                System.out.println("Consumer: Waiting to take...");
                String item = queue.take();
                System.out.println("Consumer: Received -> " + item);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}

/*
 * o/p =>
 * Producer: Putting item...
 * Consumer: Waiting to take...
 * Producer: Item handed off.
 * Consumer: Received -> Hello
 *
 * */