package com.nishchay.concurrentpkg.pool.a02fixedpool;

import com.nishchay.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class PoolOfRunnable {

    public static void main(String[] args) {
        ex1();
    }

    private static void ex1() {
        List<Runnable>  runableTaskList = new ArrayList<>();
        runableTaskList.add(() -> printName("Java"));
        runableTaskList.add(() -> printName("Python"));
        runableTaskList.add(() -> printName("Ruby"));
        runableTaskList.add(() -> printName("Go"));
        runableTaskList.add(() -> printName("Scala"));
        runableTaskList.add(() -> printName("Kotlin"));

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(6);

        for (Runnable runnableTask : runableTaskList) {
            fixedThreadPool.submit(runnableTask);
        }
        fixedThreadPool.shutdown();
    }

    private static void printName(String name) {
        System.out.println(name + " Job started by thread : " + Thread.currentThread().getName());
        Utils.sleep0(2 * 1000);
        System.out.println("Name -  : " + name);
    }

}
/*
 * Just check the thread pool thread names
 * O/P =>
 * Java Job started by thread : pool-1-thread-1
 * Kotlin Job started by thread : pool-1-thread-6
 * Python Job started by thread : pool-1-thread-2
 * Go Job started by thread : pool-1-thread-4
 * Scala Job started by thread : pool-1-thread-5
 * Ruby Job started by thread : pool-1-thread-3
 * Name -  : Java
 * Name -  : Python
 * Name -  : Scala
 * Name -  : Ruby
 * Name -  : Go
 * Name -  : Kotlin
 *
 * */