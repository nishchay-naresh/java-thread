package com.nishchay.concurrentpkg.collection;


import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueDemo {

    public static void main(String[] args) {

        pqMethod4IntegerOrdering();
        pqMethod4CustomOrdering();

    }

    private static void pqMethod4IntegerOrdering() {

       BlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
        queue.add(10);
        queue.add(2);
        queue.add(5);

        // take()will throw java.lang.InterruptedException, so using poll()
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

    private static void pqMethod4CustomOrdering() {

//        BlockingQueue<Student> queue1 = new PriorityBlockingQueue<>(5, (e1, e2) -> e1.getRank() - e2.getRank());
        BlockingQueue<Student> queue1 = new PriorityBlockingQueue<>(5, Comparator.comparingInt(Student::getRank));

        queue1.add(new Student("a", 12));
        queue1.add(new Student("b", 1));
        queue1.add(new Student("c", 4));

        System.out.println(queue1.poll());
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());

    }

}

