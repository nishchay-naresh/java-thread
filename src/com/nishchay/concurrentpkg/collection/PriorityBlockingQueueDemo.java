package com.nishchay.concurrentpkg.collection;

import com.nishchay.pojo.Student;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueDemo {

    public static void main(String[] args) {

        pqMethod4IntegerOrdering();
        pqMethod4CustomOrdering();

    }

    private static void pqMethod4IntegerOrdering() {

        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<Integer>();
        queue.add(10);
        queue.add(2);
        queue.add(5);

        // take()will throw java.lang.InterruptedException, so using poll()
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }

    private static void pqMethod4CustomOrdering(){

        PriorityBlockingQueue<Student> queue1 =
                new PriorityBlockingQueue<>(5, (e1,e2) -> e1.getRank() - e2.getRank());

        queue1.add(new Student("a", 12));
        queue1.add(new Student("b", 1));
        queue1.add(new Student("c", 4));

        System.out.println(queue1.poll());
        System.out.println(queue1.poll());
        System.out.println(queue1.poll());

    }

}

