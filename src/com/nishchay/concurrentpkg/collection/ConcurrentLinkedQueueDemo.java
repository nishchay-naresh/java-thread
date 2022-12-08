package com.nishchay.concurrentpkg.collection;


import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueDemo {

    public static void main(String[] args) {

        basicMethods();
        removeEx();

    }

    private static void basicMethods() {

        Queue<String> queue = new ConcurrentLinkedQueue<String>();

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

        Queue<String> queue = new ConcurrentLinkedQueue<String>();

        queue.add("Chennai");
        queue.add("Patna");
        queue.add("Delhi");
        queue.add("Mumbai");

        System.out.println("Removing element using remove(): " + queue.remove());
        System.out.println("The queue is " + queue);

        System.out.println("Removing element using remove(e): " + queue.remove("Delhi"));
        System.out.println("The queue is " + queue);
    }

}
