package com.nishchay.concurrentpkg.condition.oddeven;


public class Driver {

    public static final int LIMIT = 5;

    public static void main(String[] args) {

        SharedPrinter sharedPrinter = new SharedPrinter(1, 2, true);

        new Thread(() -> printOdd(sharedPrinter), "Odd  Thread").start();
        new Thread(() -> printEven(sharedPrinter), "Even Thread").start();

    }

//    instead of creating a new class for thread implementation, putting thread logic in a method
//    later invoking these method thought threads created using lambdas
    public static void printOdd(SharedPrinter sharedPrinter) {
        for (int i = 1; i <= LIMIT; i++) {
            sharedPrinter.printOdd();
        }
    }

    public static void printEven(SharedPrinter sharedPrinter) {
        for (int i = 1; i <= LIMIT; i++) {
            sharedPrinter.printEven();
        }
    }

}

