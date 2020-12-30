package com.nishchay.concurrentpkg.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetCMEDemo2Threads {

    public static void main(String[] args) throws InterruptedException {

        List<String> list = new ArrayList<>();
        list.add("java");
        list.add("go");
        list.add("perl");
        list.add("ruby");
        System.out.println("original list - " + list);
        // Exception in thread "main" java.util.ConcurrentModificationException
        // Iterator<String> itr = list.iterator();

        Thread t = new Thread(() -> addToList(list));
        t.start();
        t.join();

        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            String item = itr.next();
            System.out.println(item);
        }


        System.out.println("changed list - " + list);
    }

    public static void addToList(List<String> list){
        list.add("python");
        list.add("php");
    }
}
