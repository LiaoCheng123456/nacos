package com.example.business;

import java.util.concurrent.locks.ReentrantLock;

public class LockUtil {
    static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {


        for (int i = 0; i < 1; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock();
                }
            }).start();
        }
    }

    private static void lock() {

        lock.lock();
        for (int i = 1; i <= 3; i++) {
            System.out.println("线程开始" + i);
        }
    }

}
