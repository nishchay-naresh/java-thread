package com.nishchay.thread.threadcommunication.nthreads.splittask;

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