package com.nishchay.concurrentpkg.synchronizers.exchanger;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Exchanger;

// Producer class
public class Producer implements Runnable {
    private Country country;
    private Exchanger<Country> ex;

    Producer(Exchanger<Country> ex){
        this.ex = ex;
    }

    @Override
    public void run() {

        for(int i = 1; i <= 3; i ++){

            Country country = getCountry(i);
            try {
                // exchanging with an dummy Country object
                Country dummyCountry = ex.exchange(country);
                System.out.println("Got country object from Consumer thread : "+ dummyCountry);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

    }

    private Country getCountry(int i) {
        List<Country> countries =  Arrays.asList(
                new Country("India", "Hindi", "INR"),
                new Country("United State", "English", "USD"),
                new Country("Japan", "Japanes", "JPN")
        );
        return countries.get(i-1);
    }

}