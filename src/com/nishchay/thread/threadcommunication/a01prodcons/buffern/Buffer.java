package com.nishchay.thread.threadcommunication.a01prodcons.buffern;

public interface Buffer<T> {
    void produce(T item) ;
    T consume() ;
}
