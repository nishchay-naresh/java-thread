package com.nishchay.concurrentpkg.collection;



import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.*;

import static com.nishchay.Utils.sleep0;


/*
 * java.util.concurrent.ConcurrentLinkedQueue
 *
 * -	ConcurrentLinkedQueue is a thread-safe, unbounded queue
 * -	Stores elements in linked node
 * -	FIFO structure [ enqueue() at tail, dequeue() at head ]
 * -	Non Blocking ( internally using ArrayBQ, LinkedBQ)
 * 	        put() & take() are non-blocking
 * -	UseCase - when an unbounded Queue is shared among many threads
 * -	Not allowing null (like many other concurrent collection)
 * -	Iteration - not throwing ConcurrentModificationException
 *
 *
 * https://stackoverflow.com/questions/616484/how-to-use-concurrentlinkedqueue
 * https://www.baeldung.com/java-queue-linkedblocking-concurrentlinked
 *
 * */
public class ConcurrentLinkedQueueDemo {

    public static void main(String[] args) {

        basicMethods();
        System.out.println("\n-------------------------------");
        removeEx();
        System.out.println("-------------------------------");
        producerConsumerEx();
        System.out.println("-------------------------------");
        pcNonBlockingPut();
    }


    private static void basicMethods() {

        Queue<String> queue = new ConcurrentLinkedQueue<>();

        queue.add("Chennai");
        queue.add("Patna");
        queue.add("Delhi");
        queue.add("Bangalore");
        queue.add("Mumbai");

        System.out.println("ConcurrentLinkedQueue: " + queue);

        // Displaying the first element - using peek() method
        System.out.println("First Element is: " + queue.peek());
        // Remove and display the first element using poll() method
        System.out.println("Head Element is: " + queue.poll());

        System.out.print("ConcurrentLinkedQueue: " + queue);

        // Call iterator() method
        Iterator<String> iterator = queue.iterator();

        // Print elements of iterator
        System.out.print("\nConcurrentLinkedQueue using iterator:");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + ", ");
        }

    }

    private static void removeEx() {

        Queue<String> queue = new ConcurrentLinkedQueue<>();

        queue.add("Chennai");
        queue.add("Patna");
        queue.add("Delhi");
        queue.add("Mumbai");

        System.out.println("Removing element using remove(): " + queue.remove());
        System.out.println("The queue is " + queue);

        System.out.println("Removing element using remove(e): " + queue.remove("Delhi"));
        System.out.println("The queue is " + queue);
    }

    private static void producerConsumerEx() {

        Queue<Integer> clQueue = new ConcurrentLinkedQueue<>();
//        Queue<Integer> clQueue = new PriorityQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.execute(() -> produce(clQueue));
        executorService.execute(() -> consume(clQueue));
        executorService.execute(() -> consume(clQueue));
        executorService.execute(() -> consume(clQueue));
        executorService.execute(() -> consume(clQueue));
        executorService.execute(() -> consume(clQueue));

        executorService.shutdown();

    }

    public static void produce(Queue<Integer> queue) {
        for (int i = 1; i <= 5; i++) {
            queue.add(i);
            System.out.println(Thread.currentThread().getName() + " produces:" + i);
        }
    }

    public static void consume(Queue<Integer> queue) {
        sleep0(50);
        System.out.println(Thread.currentThread().getName() + " consume:" + queue.poll());
    }



    private static void pcNonBlockingPut(){

        int inputValue = 1;
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

        Runnable offerTask = () -> queue.offer(inputValue);

        // poll task additionally checks the queue for an element first as ConcurrentLinkedQueue is non-blocking and can return a null value.
        Callable<Integer> pollTask = () -> {
            while (queue.peek() != null) {
                return queue.poll();
            }
            return null;
        };

        executorService.submit(offerTask);
        Future<Integer> returnedFuture = executorService.submit(pollTask);
        int returnedValue;
        try {
            returnedValue = returnedFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        if(inputValue == returnedValue){
            System.out.println("inputValue == returnedValue");
        }
        executorService.shutdown();
    }

}
/*
 * O/P =
 *
 *
 * clQueue = new ConcurrentLinkedQueue<>();
 * pool-1-thread-1 consume:2
 * pool-1-thread-2 consume:1
 * pool-1-thread-3 consume:4
 * pool-1-thread-4 consume:3
 * pool-1-thread-1 consume:5
 * Each value is consumed only once with concurrent collection
 *
 * clQueue = new PriorityQueue<>();
 * pool-1-thread-4 consume:2
 * pool-1-thread-3 consume:1
 * pool-1-thread-2 consume:1
 * pool-1-thread-1 consume:1
 * pool-1-thread-4 consume:3
 * few value is consumed many times here with non-thread safe collection
 *
 * */