package com.nishchay.concurrentpkg.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicEx {

    public static void main(String[] args) {

        atomicIntegerDemo();

        atomicReferenceDemo();

    }

    public static void atomicIntegerDemo() {

        //Creating an AtomicInteger
        // AtomicInteger atomicInteger = new AtomicInteger(); - creates an AtomicInteger with the initial value 0
        AtomicInteger atomicInteger = new AtomicInteger(123);

        // Getting the AtomicInteger Value
        int theValue = atomicInteger.get();
        System.out.println("theValue = " + theValue);

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

    public static void atomicReferenceDemo() {

        // untyped AtomicReference get() example
        // AtomicReference atomicReference = new AtomicReference("first value referenced");

        // typed AtomicReference example
        AtomicReference<String> atomicReferenceTyped =
                new AtomicReference<>("first value referenced");

        String referenceValue = atomicReferenceTyped.get();
        System.out.println("referenceValue = " + referenceValue);

        // Setting the AtomicReference Reference - set() method
        AtomicReference<String> atomicReference1 = new AtomicReference<>();
        atomicReference1.set("New object referenced");
        System.out.println("atomicReference1.get() = " + atomicReference1.get());

        // Comparing and Setting the AtomicReference Reference
        String initialReference = "initial value referenced";
        AtomicReference<String> atomicStringReference = new AtomicReference<>(initialReference);

        String newReference = "new value referenced";
        boolean exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged: " + exchanged); // true

        exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged: " + exchanged); // false

    }
}
