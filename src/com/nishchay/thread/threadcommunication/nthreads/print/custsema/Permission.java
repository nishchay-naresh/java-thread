package com.nishchay.thread.threadcommunication.nthreads.print.custsema;


/*
* Permission class is a Custom Semaphore
*
* */
public class Permission {

    private boolean isAllowed;

    Permission() {
        isAllowed = false;
    }

    public void setPermission() {
        isAllowed = true;
    }

    public boolean getPermission() {
        return isAllowed;
    }

    public void revokePermission() {
        isAllowed = false;
    }

}