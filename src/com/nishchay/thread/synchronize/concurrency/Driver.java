package com.nishchay.thread.synchronize.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Driver {

    public static void main(String[] args) {

//        ccyIssueSingleThread();
//        ccyIssueMultiThread();
        ccyIssueMultiThreadFixedUsingSync();

    }



    // single threaded - no issue
    private static void ccyIssueSingleThread() {
        ExecutorService es= Executors.newFixedThreadPool(5);
        BankAccount account = new BankAccount(100);
        Worker worker= new Worker(account);
        es.submit(worker);

        es.shutdown();
    }

    // multi-thread - issue will be there
    private static void ccyIssueMultiThread() {
        ExecutorService es = Executors.newFixedThreadPool(5);
        BankAccount account = new BankAccount(100);
        for (int i = 0; i < 5; i++) {
            Worker worker = new Worker(account);
            es.submit(worker);
        }
        es.shutdown();
    }


    // multi-thread - issue is being fixed by synchronization
    private static void ccyIssueMultiThreadFixedUsingSync() {
        ExecutorService es = Executors.newFixedThreadPool(5);
        SyncBankAccount account = new SyncBankAccount(100);
        for (int i = 0; i < 5; i++) {
            Worker worker = new Worker(account);
            es.submit(worker);
        }
        es.shutdown();
    }

}
