# java-thread
Java threading concepts


### spawning a thread
To spawn a new Thread, get it executed as independent path of execution.
One need 3 things :
1. Task - in the form of Runnable implementation
2. Worker - a Thread class object
3. Assigning task to the Worker - Passing task to the worker & invoking t.start()

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


### demon thread


### method to prevent thread execution
1.	t.join()
2.	t.interrupt()
3.	Thread.sleep()
4.	Thread.yield()

### synchronization
1.	sharing single resource across multiple threads
2.	read-write problem
3.	deadlock

### inter-thread communication
1.	wait
2.	notify()
3.	notifyAll()

### Uncaught Exception in Threads
+	specific exception handler for each of the thread
+	generic exception handler for all of the thread

# java.util.concurrent Package
Exploring java concurrent Package



###Lock

###Callable & Future

###ThreadLocal

###Concurrent Collection

+	CopyOnWriteArrayList
+	CopyOnWriteArraySet


+	BlockingQueue 
+	ArrayBlockingQueue 
+	LinkedBlockingQueue


+	ConcurrentHashMap


###ThreadPool

+	Executors.newSingleThreadExecutor();
+	Executors.newFixedThreadPool(1);
+	Executors.newCachedThreadPool();
+	ForkJoinPool


###Synchronizers

1.	Semaphore 
2.	CountDownLatch
3.	CyclicBarrier
4.	SynchronousQueue
5.	Exchanger
6.	Phaser



 
