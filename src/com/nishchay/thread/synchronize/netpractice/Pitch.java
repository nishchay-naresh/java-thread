package com.nishchay.thread.synchronize.netpractice;

public class Pitch {

    // using synchronization keyword
    public void practice(String playerName) {
        synchronized (this) {
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " Currently practicing - " + playerName);
            }
        }
    }

    /*
class Pitch {
    // using reentrant lock
	Lock lock = new ReentrantLock();
	public void practice(String name) {
        lock.lock();
		for (int i = 1; i <= 5; i++) {
			 System.out.println(Thread.currentThread().getName() + " Currently practicing - " + name);
		}
        lock.unlock();
	}
}
*/

}