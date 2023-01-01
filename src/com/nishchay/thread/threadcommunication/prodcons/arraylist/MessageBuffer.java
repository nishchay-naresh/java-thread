package com.nishchay.thread.threadcommunication.prodcons.arraylist;

import java.util.ArrayList;
import java.util.List;

import static com.nishchay.Utils.wait0;

public class MessageBuffer {

    private final List<String> messageList;
    private final int limit;

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
            wait0(this);
        }
        // putting the element at the end
        messageList.add(msg);
        this.notify();
    }

    public synchronized String dequeue() {

        while (isEmpty()) {
            wait0(this);
        }

        // picking up the element from start
        String message = messageList.remove(0);
        this.notify();
        return message;
    }

}
