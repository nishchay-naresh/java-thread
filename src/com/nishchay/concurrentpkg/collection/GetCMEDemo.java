package com.nishchay.concurrentpkg.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetCMEDemo {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(9);
        list.add(2);
        list.add(7);
        list.add(3);
        list.add(5);
        System.out.println("original list - " + list);

        for_loop_iteration(list);
//        for_each_iteration(list);
//        iterating_using_iterator(list);

        System.out.println("\nmodified list - " + list);
    }

    private static void for_loop_iteration(List<Integer> list) {

        // for loop is safe against ConcurrentModificationException
        for (int i = 0; i < list.size(); i++) {
            if (i == 2) {
                list.remove(2);
            }
        }
        System.out.println("List after for-loop iteration - " + list);
    }

    private static void for_each_iteration(List<Integer> list) {

        // Exception in thread "main" java.util.ConcurrentModificationException - for either of them add/remove
        for (int x : list) {
            if (x == 3)
                list.add(100);
        }
        System.out.println("List after for-each iteration - " + list);
    }

    private static void iterating_using_iterator(List<Integer> list) {

        // Exception in thread "main" java.util.ConcurrentModificationException
        Iterator<Integer> itr = list.iterator();
        while (itr.hasNext()) {
            Integer x = itr.next();
            if (x == 5) {
                list.remove(x);
                //  iterator's own remove method - is safe against ConcurrentModificationException
                //  itr.remove();
            }
        }
        System.out.println("List after loop iteration - " + list);
    }



}
