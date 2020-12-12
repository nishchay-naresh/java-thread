package com.nishchay.thread.jmm;

public class VolatileExample {

    int x = 0, y = 0, z = 0;
    volatile boolean v;

    public void writer() {
        x = 42;
        y = 43;
        z = 44;
        v = true;
    }

    public void reader() {
        int r1, r2, r3;
        if (v == true) {
            // guarantee to see 42, bcus x can be cached as well
            r1 = x ;
            r2 = y;
            r3 = z;
        }
    }
}

/*
*   x = 42
*   v = true -> creating a barrier. (All the things prior to this are happens before)
*               // write operation -> write barrier
*               // read operation -> read barrier
*   y = 43
*   z = 44
*
* */

/*
*   Volatile solves below 3 issues of java concurrency :
    1. Visibility                   <-- by ensuring read/write from main memory
    2. Happens Before               <-- Ensuring these two by creating barrier
    3. Instruction Reordering 
*
* */