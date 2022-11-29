package com.nishchay.thread.synchronize.concurrency;

public class Worker implements Runnable {

    private Account account;

    public Worker(Account account) {
        this.account = account;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            int startBalance = account.getBalance();
            account.deposit(10);
            int endBalance = account.getBalance();
            System.out.println("Start Balance = " + startBalance + " End Balance = " + endBalance + " Worker - " + Thread.currentThread().getName());
        }
    }
}

interface Account{
    int getBalance();
    void deposit(int amount);
}
class BankAccount implements Account {

    private int balance;

    public BankAccount(int startBalance) {
        balance = startBalance;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }
}

class SyncBankAccount implements Account {

    private int balance;

    public SyncBankAccount(int startBalance) {
        balance = startBalance;
    }

    public int getBalance() {
        return balance;
    }

    public synchronized void deposit(int amount) {
        balance += amount;
    }
}