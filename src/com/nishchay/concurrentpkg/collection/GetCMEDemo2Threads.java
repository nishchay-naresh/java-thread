package com.nishchay.concurrentpkg.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GetCMEDemo2Threads {

    public static void main(String[] args) throws InterruptedException {

        List<String> list = new ArrayList<>();
//        List<String> list = new CopyOnWriteArrayList<>(); // safe against ConcurrentModificationException
        list.add("java");
        list.add("go");
        list.add("perl");
        list.add("ruby");
        System.out.println("original list - " + list);

//         Exception in thread "main" java.util.ConcurrentModificationException
         Iterator<String> itr = list.iterator(); // iterator should assign prior to structural change

        Thread t = new Thread(() -> addToList(list));
        t.start();
        t.join();

//        Iterator<String> itr = list.iterator(); // if we take the iterator post structural modification, we won't get CME
        while (itr.hasNext()) {
            String item = itr.next();
            System.out.println(item);
        }
        System.out.println("changed list - " + list);
    }

    private static void addToList(List<String> list){
        list.add("python");
        list.add("php");
        System.out.println("modified list -" + list);
    }
}
