/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaranch13;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Piet
 */
public class AtSvmGarg {
    Lock lock;
    int count = 0, maxcount = 100;
    
    public static void main(String... args) {
        new AtSvmGarg().go();
    }
       
    private void go() {
        lock = new ReentrantLock();
        Runnable r1 = this::even;
        Runnable r2 = this::odd;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        if (count > 100)  {
            t1.stop();
            t2.stop();
        }
    }
    
    private void even() {
        for (int i = count; i <= maxcount; i++) {
            try {
                lock.lock();
                if (i % 2 == 0) System.out.println("even runnable: count = " + i);
            }
            finally {
                lock.unlock();
            }  
        }
    }
    
    private void odd() {
        for (int i = count; i <= maxcount; i++) {
            try {
                lock.lock();
                if (i % 2 == 1) System.out.println("odd runnable: count = " + i);
            }
            finally {
                lock.unlock();
            }     
        }
         
    }
    
}
