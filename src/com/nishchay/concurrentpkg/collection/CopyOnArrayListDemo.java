package com.nishchay.concurrentpkg.collection;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnArrayListDemo {

    public static void main(String[] args) {

        List<Integer> list = new CopyOnWriteArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);

        for (Integer i1 : list) {
            System.out.println(i1);
            list.add(100);
        }
        System.out.println(list);
    }
}
