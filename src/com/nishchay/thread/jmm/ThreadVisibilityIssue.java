package com.nishchay.thread.jmm;

public class ThreadVisibilityIssue {

//    private static boolean sayHello = false;
    private static volatile boolean sayHello = false;

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> greeting());
        thread.start();

        Thread.sleep(1000);
        System.out.println("Say Hello..");
        sayHello = true;

        Thread.sleep(1000);
        System.out.println("Say Bye..");
        sayHello = false;
    }

    private static void greeting(){

        while (!sayHello) {
        }
        System.out.println("Hello World!");

        while (sayHello) {
        }
        System.out.println("Good Bye!");
    }
}

/*
 *
 * ----- Ideal Output -----
 * Say Hello..
 * Hello World!
 * Say Bye..
 * Good Bye!
 *
 *  Yes! That is what Thread visibility issue
 *  The first thread is unaware of the changes done by the main thread to the sayHello variable.
 *  One can use volatile keyword to avoid memory consistency errors.
 *
 * --- Actual Output ----
 * Say Hello..
 * Say Bye..
 *
 *
 * */