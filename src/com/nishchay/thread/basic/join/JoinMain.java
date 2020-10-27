package com.nishchay.thread.basic.join;

/*
*   Joining Main thread for the completion of main thread itself Which will never happen
*   It's a rare scenario to form a deadlock with a single thread (which is main itself)
*
*/
public class JoinMain {

    public static void main(String[] args) {

        try {
            Thread.currentThread().join();
            System.out.println("Joining main thread for the completion of main thread itself");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/*
* O/P => no output, but deadlock
 */