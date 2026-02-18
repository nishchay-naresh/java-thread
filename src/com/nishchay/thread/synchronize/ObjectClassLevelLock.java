package com.nishchay.thread.synchronize;

/*
 * ================= Object level and Class level locks in Java ===================
 * There are two types of locking in java in threading.
 *  1.  Object level lock
 *  2.  Class level lock
 *  Object-level lock synchronizes on the current instance, so different objects have independent locks.
 *  Class-level lock synchronizes on the Class object, meaning it applies across all instances of that class.
 *
 * https://adnjavainterview.blogspot.com/2019/06/object-level-and-class-level-locks-in-java.html
 * */
public class ObjectClassLevelLock {

    public static void main(String[] args) throws InterruptedException {
        getIMSException();
    }

/*
    static class ObjectLevelLockEx {
        private final Object lock = new Object();
        public void syncMethod1() {
            synchronized (lock) {
                //block implementation here
            }
        }

        public synchronized void syncMethod2() {
            //method implementation here
        }

        public void syncMethod3() {
            synchronized (this) {
                //block implementation here
            }
        }
    }

    static class ClassLevelLockEx {
        private final static Object lock = ClassLevelLockEx.class;
        public void syncMethod1() {
            synchronized (lock) {
                //block implementation here
            }
        }

        public synchronized static void syncMethod2() {
            //method implementation here
        }

        public void syncMethod3() {
            synchronized (ClassLevelLockEx.class) {
                //block implementation here
            }
        }
    }

*/

    /*
     *
     * IllegalMonitorStateException thrown when a thread calls wait(), notify(), or notifyAll()
     * on an object without holding that object’s monitor lock
     * Meaning the call is made outside a synchronized block on the same object /
     *
     *  instanceMethod locks this
     *  staticMethod locks Demo.class
     *  Different locks
     *
     * */
    private static void getIMSException() throws InterruptedException {

        ObjectClassLevelLock ref = new ObjectClassLevelLock();

        // execute below code one after another by commenting them
        // calling wait() from an instance synchronized method after holding monitor lock - will succeed
        ref.instanceMethod();

        // calling wait() from an instance method without holding monitor lock - will throw Exception
        ref.instanceMethod1();

        // calling wait() from an static synchronized method after holding class level lock - will succeed
        staticMethod();

        // calling wait() from an instance method without holding monitor lock - will throw Exception
        staticMethod1();
    }

    public synchronized void instanceMethod() throws InterruptedException {
        wait(2 * 1000);
    }

    public void instanceMethod1() throws InterruptedException {
        wait();             // throw java.lang.IllegalMonitorStateException
        // notify();        // throw java.lang.IllegalMonitorStateException
        // notifyAll();     // throw java.lang.IllegalMonitorStateException
    }

    public static synchronized void staticMethod() throws InterruptedException {
        ObjectClassLevelLock.class.wait(2 * 1000);
    }

    public static void staticMethod1() throws InterruptedException {
        ObjectClassLevelLock.class.wait();  // throw java.lang.IllegalMonitorStateException
    }
}
