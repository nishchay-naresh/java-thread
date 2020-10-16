package com.nishchay.thread.basic.spawnthread;

public class ThreadSpawnDemo {

    public static void main(String[] args) {

        ThreadSpawnDemo ref = new ThreadSpawnDemo();

        ref.spawnAndRunNewThread();
//        ref.startingThreadTwice();
    }

    private void spawnAndRunNewThread(){

        /*
        * To spawn a new Thread, get it executed as independent path of execution :
        * One need 3 things :
        * 1. Task - in the form of Runnable implementation
        * 2. Worker - a Thread class object
        * 3. Assigning task to the Worker - Passing task to the worker & invoking t.start()
        *
        * */

        Task task = new Task();
        Thread userThread =  new Thread(task);
        userThread.start();

        for (int i = 1; i <= 5; i++) {
            System.out.println("Main Thread is counting - " + i);
        }

    }

    private void startingThreadTwice(){

        Thread t =  new Thread(new Task());
        t.start();

        // java.lang.IllegalThreadStateException
        // t.start();

    }

}
