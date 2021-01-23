package com.nishchay.thread.threadcommunication.nthreads.print.bool;

public class MyTask implements Runnable {

	private SharedObject obj;
	private int value;

	public MyTask(SharedObject obj, int value) {
		this.obj = obj;
		this.value = value;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			
			switch(value){
			case 1 : 
				obj.printOne(value);
				break;
			case 2 : 
				obj.printTwo(value);
				break;
			case 3 : 
				obj.printThree(value);
				break;
			case 4 : 
				obj.printFour(value);
				break;
			default:
				System.out.println("Invalid value");
				break;
				
			}
			
		}
	}
}