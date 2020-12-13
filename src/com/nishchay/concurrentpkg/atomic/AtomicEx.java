package com.nishchay.concurrentpkg.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicEx {

    public static void main(String[] args) {

        atomicIntegerDemo();

    }

    public static void atomicIntegerDemo() {

        //Creating an AtomicInteger
        // AtomicInteger atomicInteger = new AtomicInteger(); - creates an AtomicInteger with the initial value 0
        AtomicInteger atomicInteger = new AtomicInteger(123);

        // Getting the AtomicInteger Value
        int theValue = atomicInteger.get();

        // Setting the AtomicInteger Value
        atomicInteger.set(234);

        // Compare and Set the AtomicInteger Value
        atomicInteger = new AtomicInteger(123);
        int expectedValue = 123;
        int newValue = 234;
        atomicInteger.compareAndSet(expectedValue, newValue);

        // Adding to the AtomicInteger Value
        // addAndGet() - adds a number to the AtomicInteger and returns its value after the addition
        // getAndAdd() - adds a number to the AtomicInteger but returns the value the AtomicInteger had before the value was added
        atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger.getAndAdd(10));
        System.out.println(atomicInteger.addAndGet(10));

        // getAndIncrement()
        // incrementAndGet()
        //The methods getAndIncrement() and incrementAndGet() works like getAndAdd() and addAndGet() but just add 1 to the value of the AtomicInteger.

        //Subtracting From the AtomicInteger Value
        // decrementAndGet() - subtracts 1 from the AtomicInteger value and returns its value after the subtraction
        // getAndDecrement() - subtracts 1 from the AtomicInteger value but returns the value the AtomicInteger had before the subtraction.

    }
}
