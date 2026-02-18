# java-thread
Java threading concepts


### spawning a thread
To spawn a new Thread and get it executed in a new path of execution:
One need 4 things:
1. Task - in the form of Runnable implementation
2. Worker - Thread class object
3. Assigning this task to the Worker - Thread(Runnable r)
4. call start() to execute it in a new path of execution

``` 
        // java8 way to create a thread - Providing Runnable interface implementation using lambda expression
        Thread t1 = new Thread(() -> System.out.println("Creating a thread using lambda expression"));
        t1.start();
```
### starting a thread twice - IllegalThreadStateException

### extends thread vs implements runnable

### getting & setting name of a thread

### thread states and transitions
A thread can be in one of the following states:
+	NEW
+	RUNNABLE
+	BLOCKED
+	WAITING
+	TIMED_WAITING
+	TERMINATED

### thread priorities
+   Thread.MIN_PRIORITY = 1
+   Thread.NORM_PRIORITY = 5
+   Thread.MAX_PRIORITY = 10


### demon thread

*  A daemon thread in Java is a background thread that supports user threads.
*  The JVM does not wait for daemon threads to complete execution.
*  When all user threads finish, the JVM terminates daemon threads automatically.

### method to prevent thread execution
1.	t.join()
``` 
        t.join();
        The thread that calls join() pauses
        Until thread t completes execution
``` 
2.	t.interrupt()
3.	Thread.sleep()
4.	Thread.yield()

**Thread interrupts** - Interrupts are co-operative mechanism for indicating stop signal to a thread ( java.lang.InterruptedException )
### synchronization
1.	Mutual-Exclusion - sharing single resource across multiple threads

Once can achieve the synchronization effect in two ways :
+   By synchronized method
+   By synchronized bocks

And the Synchronized object lock could be
+   Object level lock - instance method / block
+   Class level lock - static method / block 

2.	data race - read write problem
3.	deadlock

### inter-thread communication
1.	wait
2.	notify()
3.	notifyAll()

### Uncaught Exception in Threads
+	specific exception handler for each of the threads
+	generic exception handler for all the thread

# java.util.concurrent Package
Exploring java concurrent Package


### Lock & Condition
+   java.util.concurrent.locks.Lock
+   java.util.concurrent.locks.ReentrantLock


+   java.util.concurrent.locks.ReadWriteLock
+   java.util.concurrent.locks.ReentrantReadWriteLock

### Callable & Future

### ThreadLocal

### Concurrent Collection

+	CopyOnWriteArrayList
+	CopyOnWriteArraySet


+	BlockingQueue 
+	ArrayBlockingQueue 
+	LinkedBlockingQueue


+	ConcurrentHashMap


### ThreadPool

+	Executors.newSingleThreadExecutor();
+	Executors.newFixedThreadPool(1);
+	Executors.newCachedThreadPool();
+	ForkJoinPool


### Synchronizers

1.	Semaphore - a synchronizer to maintain a set of permits
2.	CountDownLatch - Main task waits on latch.await(), till all sub-task execute their latch.countDown()
3.	CyclicBarrier - Parallel threads are waiting for each other to reach a common point before continuing execution
4.	SynchronousQueue - Zero-capacity handoff queue
5.	Exchanger - Two threads swap data at a rendezvous point
6.	Phaser - can act like a CountDownLatch / CyclicBarrier with dynamic Registries & De-Register feature




