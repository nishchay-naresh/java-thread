package com.nishchay.concurrentpkg.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GetCMEDemo {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(9, 2, 7, 3, 5);
        System.out.println("original list - " + list);
        for_loop_iteration(list);
        iterating_using_iterator(list);
        for_each_iteration(list);
        System.out.println("modified list - " + list);

        failFastBehaviour();
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
     * 	// it's using an Iterator internally
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
     *   list.add("z") Throws ConcurrentModificationException always
     * But
     *   list.remove("c"); will not Throws ConcurrentModificationException why?
     *
     *	Here’s what happens:
     *		-	The iterator had already fetched till "c"
     *		-	Internal cursor moves to index 2
     *		-	But now size is 3
     *		-	So hasNext() becomes false
     *		-	So the loop ends before calling next() again, and therefore
     *		-	The modification check never runs again
     *		-	No exception thrown
     *
     * */
    private static void failFastBehaviour() {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        // List<String> list = Arrays.asList("a", "b", "c", "d");

        for (String s : list) {
            if ("c".equals(s)) {
                // list.add("z");
                list.remove("c");
            }
        }
        System.out.println(list);
    }
}
