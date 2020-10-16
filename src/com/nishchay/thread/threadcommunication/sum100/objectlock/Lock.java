package com.nishchay.thread.threadcommunication.sum100.objectlock;

// Boolean is immutable class, so Writing own lock class for Boolean mutability
class Lock {

    boolean childTurn;

    public Lock(boolean childTurn) {
        this.childTurn = childTurn;
    }

    public boolean isChildTurn() {
        return childTurn;
    }

    public void setChildTurn(boolean childTurn) {
        this.childTurn = childTurn;
    }
}