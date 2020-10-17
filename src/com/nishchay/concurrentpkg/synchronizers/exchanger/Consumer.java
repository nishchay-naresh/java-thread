package com.nishchay.concurrentpkg.synchronizers.exchanger;

import java.util.concurrent.Exchanger;

// Consumer class
public class Consumer implements Runnable {

    private Country country;
    private Exchanger<Country> ex;

    Consumer(Exchanger<Country> ex){
        this.ex = ex;
    }


    @Override
    public void run() {
        for(int i = 1; i <= 3; i ++){

            try {
                // Getting Country object from producer thread, giving dummy country object in return
                Country dummyCountry = new Country("Dummy", "DummyLang","CCY");
                Country country = ex.exchange(dummyCountry);
                System.out.println("Got country object from Producer thread : "+ country);

            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}
