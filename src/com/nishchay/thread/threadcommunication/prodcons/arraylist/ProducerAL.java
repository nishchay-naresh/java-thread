package com.nishchay.thread.threadcommunication.prodcons.arraylist;

class ProducerAL extends Thread {
    private final MessageBuffer queue;

    public ProducerAL(MessageBuffer queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for(int i=1; i <= 10; i++) {
            String msg = "Hello-" + i;
            queue.enqueue(msg);
            System.out.println(Thread.currentThread().getName() + " produced - " + msg);
        }
    }
}
