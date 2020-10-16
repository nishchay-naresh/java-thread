package com.nishchay.thread.threadcommunication.prodcons.arraylist;

import java.util.ArrayList;
import java.util.List;

public class MessageBuffer {

    private List<String> messageList;
    private int limit;

    public MessageBuffer(int limit) {
        this.limit = limit;
        messageList = new ArrayList<String>(limit);
    }

    public boolean isFull() {
        return messageList.size() == limit;
    }

    public boolean isEmpty() {
        return messageList.size() == 0;
    }

    public synchronized void enqueue(String msg) {

        while (isFull()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // putting the element at the end
        messageList.add(msg);
        this.notify();
    }

    public synchronized String dequeue() {

        while (isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // picking up the element from start
        String message = messageList.remove(0);
        this.notify();
        return message;
    }

}
