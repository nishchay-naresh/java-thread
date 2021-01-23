package com.nishchay.thread.threadcommunication.nthreads.print.bool;

public class SharedObject {

	private boolean print1, print2, print3, print4;

	public SharedObject() {

		print1 = true;
		print2 = print3 = print4 = false;
	}


	public synchronized void printOne(int value) {
		while (!print1) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " : " + value);
		print1 = print3 = print4 = false;
		print2 = true;
		notifyAll();
	}

	public synchronized void printTwo(int value) {
		while (!print2) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " : " + value);
		print1 = print2 = print4 = false;
		print3 = true;
		notifyAll();
	}

	public synchronized void printThree(int value) {
		while (!print3) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " : " + value);
		print1 = print2 = print3 = false;
		print4 = true;
		notifyAll();
	}

	public synchronized void printFour(int value) {
		while (!print4) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " : " + value);
		print2 = print3 = print4 = false;
		print1 = true;
		notifyAll();
	}

}