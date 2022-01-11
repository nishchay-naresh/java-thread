package com.nishchay.concurrentpkg.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicEx {

    public static void main(String[] args) {

        atomicIntegerDemo();
        atomicIntegerFailEx();
        atomicReferenceDemo();
        atomicStampedReferenceDemo();

    }


    public static void atomicIntegerDemo() {

        // AtomicInteger atomicInteger = new AtomicInteger(); - creates an AtomicInteger with the initial value 0
        // the default initial value of a AtomicInteger object will always be 0
        System.out.println("default initial value of a AtomicInteger object = " + new AtomicInteger().get());


        AtomicInteger atomicInteger = new AtomicInteger(123);

        int theValue = atomicInteger.get();
        System.out.println("theValue = " + theValue);

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

    private static void atomicIntegerFailEx() {
        AtomicInteger atomicInteger = new AtomicInteger(123);
        int expectedValue = 123;
        int newValue = 234;
        System.out.println(atomicInteger.compareAndSet(expectedValue, newValue));
        System.out.println("curr value - " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(500, 1000));
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

    private static void atomicStampedReferenceDemo() {

        // Creating a Typed AtomicStampedReference
        String initialRef = null;
        int initialStamp = 0;
        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>(initialRef, initialStamp);

        // Getting the AtomicStampedReference Reference
        // Getting the AtomicStampedReference Stamp
        System.out.println("atomicStampedReference.getReference() = " + atomicStampedReference.getReference());
        System.out.println("atomicStampedReference.getStamp() = " + atomicStampedReference.getStamp());


        initialRef = "first text";
        atomicStampedReference = new AtomicStampedReference<>(initialRef, 0);
        System.out.println("atomicStampedReference.getReference() = " + atomicStampedReference.getReference());
        System.out.println("atomicStampedReference.getStamp() = " + atomicStampedReference.getStamp());


        // Getting Reference and Stamp Atomically
        initialRef = "text";
        initialStamp = 1;

        atomicStampedReference = new AtomicStampedReference<>(initialRef, initialStamp);

        int[] stampHolder = new int[1];
        String ref = atomicStampedReference.get(stampHolder);
        System.out.println("ref = " + ref);
        System.out.println("stamp = " + stampHolder[0]);

        // Setting the AtomicStampedReference Reference
        atomicStampedReference = new AtomicStampedReference<>(null, 0);
        String newRef = "New object referenced";
        int newStamp = 1;

        atomicStampedReference.set(newRef, newStamp);
        System.out.println("atomicStampedReference.getReference() = " + atomicStampedReference.getReference());
        System.out.println("atomicStampedReference.getStamp() = " + atomicStampedReference.getStamp());

        //---------Comparing and Setting the AtomicStampedReference Reference

        initialRef = "initial value referenced";
        initialStamp = 0;

        AtomicStampedReference<String> atomicStringReference = new AtomicStampedReference<>(initialRef, initialStamp);

        newRef = "new value referenced";
        newStamp = initialStamp + 1;

        boolean exchanged = atomicStringReference
                .compareAndSet(initialRef, newRef, initialStamp, newStamp);
        System.out.println("exchanged: " + exchanged);  //true

        exchanged = atomicStringReference
                .compareAndSet(initialRef, "new string", newStamp, newStamp + 1);
        System.out.println("exchanged: " + exchanged);  //false

        exchanged = atomicStringReference
                .compareAndSet(newRef, "new string", initialStamp, newStamp + 1);
        System.out.println("exchanged: " + exchanged);  //false

        exchanged = atomicStringReference
                .compareAndSet(newRef, "new string", newStamp, newStamp + 1);
        System.out.println("exchanged: " + exchanged);  //true


        // ----------------------- The code below shows how to detect the A-B-A situation using the AtomicStampedReference:
/*
        int[] stampHolder = new int[1];
        Object ref = atomicStampedReference.get(stampHolder);

        if(ref == null){
            //prepare optimistic modification
        }

        //if another thread changes the
        //reference and stamp here,
        //it can be detected

        int[] stampHolder2 = new int[1];
        Object ref2 = atomicStampedReference.get(stampHolder);

        if(ref == ref2 && stampHolder[0] == stampHolder2[0]){
            //no modification since optimistic modification was prepared

        } else {
            //retry from scratch.
        }
*/

    }

}
