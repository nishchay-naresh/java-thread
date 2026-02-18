package com.nishchay.thread.threadcommunication.a01prodcons.buffer1;

public interface BufferSingle {
    void produce(int item) ;
    int consume() ;
}
