package com.nishchay.concurrentpkg.callable;

import java.util.Random;
import java.util.concurrent.Callable;

public class CallableTask implements Callable<Integer> {

	public Integer call() throws Exception {
		Random generator = new Random();
		Integer randomNumber = generator.nextInt(100);
		Thread.sleep( 1000);
		return randomNumber;
	}

}