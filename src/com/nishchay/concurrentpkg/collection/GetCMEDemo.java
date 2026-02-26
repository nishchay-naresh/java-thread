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
        iterating_using_iterator(list);
        for_each_iteration(list);
        failFastBehaviour();
        System.out.println("modified list - " + list);
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

    private static void iterating_using_iterator(List<Integer> list) {

        // Exception in thread "main" java.util.ConcurrentModificationException
        Iterator<Integer> itr = list.iterator();
        while (itr.hasNext()) {
            Integer x = itr.next();
            if (x == 5) {
                list.remove(x);
                //  iterators own remove method - is safe against ConcurrentModificationException
                //  itr.remove();
            }
        }
        System.out.println("List after loop iteration - " + list);
    }

    /*
     * 	This for each loop  - for (String s : list)
     * 	is internally translated to:
     * 	// its using an Iterator internally.
     * 	Iterator<String> it = list.iterator();
     * 	while (it.hasNext()) {
     * 	    String s = it.next();
     * 	    ...
     * 	}
     *
     *
     * 	How ArrayList Detects Modification
     * 	ArrayList maintains: modCount
     * 	Every structural modification (add, remove, clear) increments modCount.
     *
     * 	When iterator is created: expectedModCount = modCount
     * 	On every next() call, it checks:
     * 	if (modCount != expectedModCount)
     *   throw ConcurrentModificationException;
     *	This is called fail-fast behavior.
     *
     * */
    private static void for_each_iteration(List<Integer> list) {

        // Exception in thread "main" java.util.ConcurrentModificationException - for either of them add/remove
        for (int x : list) {
            if (x == 3)
                //list.add(100);
                list.remove(3);
        }
        System.out.println("List after for-each iteration - " + list);
    }

    /*
     * Here
     *   list.add("Z") Throws ConcurrentModificationException always
     * But
     *   list.remove("B"); will not Throws ConcurrentModificationException why?
     *
     *	Here’s what happens:
     *		-	The iterator had already fetched "A"
     *		-	Internal cursor moves to index 1
     *		-	But now size is 1
     *		-	So hasNext() becomes false
     *		-	So the loop ends before calling next() again, and therefore
     *		-	The modification check never runs again
     *		-	No exception thrown
     *
     * */
    private static void failFastBehaviour() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");

        for (String s : list) {
            if ("A".equals(s)) {
                // list.add("Z");
                list.remove("A");
            }
        }
        System.out.println(list);
    }
}
