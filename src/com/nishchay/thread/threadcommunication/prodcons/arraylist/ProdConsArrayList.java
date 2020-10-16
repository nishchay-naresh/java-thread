package com.nishchay.thread.threadcommunication.prodcons.arraylist;

// Producer and Consumer problem
//    producer --> messageQueue <-- consumer.
public class ProdConsArrayList {

    public static void main(String[] args) {
        final int BUFFER_SIZE = 3;
        MessageBuffer queue = new MessageBuffer(BUFFER_SIZE);

        new Thread(new ProducerAL(queue), "Producer Thread").start();
        new Thread(new ConsumerAL(queue), "Consumer Thread").start();

    }
}

