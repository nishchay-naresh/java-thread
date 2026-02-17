package com.nishchay.concurrentpkg.synchronizers.exchanger;

import java.util.concurrent.Exchanger;

/*
* Exchanger = Two threads swap data at a meeting point
* Producer -- (country) -->
*                           Exchanger.exchange()
*                                               <-- (dummy country) -- Consumer
* */
public class ExchangerExample {

    public static void main(String[] args) {

        Exchanger<Country> exchanger = new Exchanger<>();
        // Starting two threads
        new Thread(new Producer(exchanger), "Producer Thread").start();
        new Thread(new Consumer(exchanger), "Consumer Thread").start();
    }
}
