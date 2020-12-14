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
        list.add(12);
        list.add(5);
        System.out.println("original list - " + list);
/*
        // for loop is safe against ConcurrentModificationException
        for(int i=0; i<list.size();i++){
            if(i==2){
                list.remove(2);
            }
        }


        // Exception in thread "main" java.util.ConcurrentModificationException - for either of them add/remove
        for(int x : list){
            System.out.println(x);
            if(x==7)
                list.add(100);
        }
*/
        // Exception in thread "main" java.util.ConcurrentModificationException
        Iterator<Integer> itr = list.iterator();
        while (itr.hasNext()) {
            Integer x = itr.next();
            if (x == 7) {
                list.remove(x);
                //  iterator's own remove method - is safe against ConcurrentModificationException
                //  itr.remove();
            }
        }


        System.out.println("changed list - " + list);
    }
}
