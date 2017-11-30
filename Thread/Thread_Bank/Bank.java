package com.Sort.Thread.Thread_Bank;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunds;

    public Bank(int n,double initialBalance){
        accounts=new double[n];
        Arrays.fill(accounts,initialBalance);
        bankLock=new ReentrantLock();
        sufficientFunds=bankLock.newCondition();
    }

    public void transfer1(int from,int to,double amount) throws InterruptedException {
        bankLock.lock();
        try {
            while (accounts[from]<amount)
                sufficientFunds.await();
            System.out.println(Thread.currentThread());
            accounts[from]-=amount;
            System.out.printf(" %10.2f frome %d to %d",amount,from,to);
            accounts[to]+=amount;
            System.out.printf("Total Balance: %10.2f%n",getTotalBalance());
            sufficientFunds.signalAll();
        }finally {
            bankLock.unlock();
        }
    }

    public synchronized void transfer2(int from,int to,double amount) throws InterruptedException {
        while (accounts[from]<amount)
            wait();
        System.out.println(Thread.currentThread());
        accounts[from]-=amount;
        System.out.printf(" %10.2f frome %d to %d",amount,from,to);
        accounts[to]+=amount;
        System.out.printf("Total Balance: %10.2f%n",getTotalBalance());
    }

    public double getTotalBalance() {
        bankLock.lock();
        try{
            double sum=0;

            for (double a:accounts)
                sum+=a;

            return sum;
        }finally {
            bankLock.unlock();
        }
    }

    public int size(){
        return accounts.length;
    }
}
