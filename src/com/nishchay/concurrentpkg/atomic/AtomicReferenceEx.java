package com.nishchay.concurrentpkg.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceEx {

    public static void main(String[] args) {
        sample();
        sample1();
    }

    private static void sample() {

        // Creating a Typed AtomicReference
        AtomicReference<String> atomicReference = new AtomicReference<>("java");
        System.out.println("initial Reference = " + atomicReference.get());

        String initialReference = "the initially referenced string";
        AtomicReference<String> atomicStringReference = new AtomicReference<>(initialReference);

        // Getting the AtomicReference Reference
        String reference = atomicStringReference.get();
        System.out.println("initial Reference = " + reference);

        // Setting the AtomicReference Reference
        atomicStringReference.set("New object referenced");
        System.out.println("updated Reference = " + atomicStringReference.get());

        // Comparing and Setting the AtomicReference Reference
        initialReference = "initial value referenced";
        atomicStringReference = new AtomicReference<>(initialReference);

        String newReference = "new value referenced";
        boolean exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged: " + exchanged);

        exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
        System.out.println("exchanged: " + exchanged);
    }

    private static void sample1() {
        AtomicReference<String> ref = new AtomicReference<>("Initial");
        // Compare and set
        boolean updated = ref.compareAndSet("Initial", "Updated");
        System.out.println("Updated? " + updated);
        System.out.println("Value: " + ref.get());
    }
}
