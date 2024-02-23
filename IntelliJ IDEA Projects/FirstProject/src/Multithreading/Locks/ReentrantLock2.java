package Multithreading.Locks;

import java.util.concurrent.locks.ReentrantLock;

class Rentrent
{
    ReentrantLock l = new ReentrantLock();

    void display1() throws InterruptedException
    {
        //        l.lock();
        //        System.out.println("Taking lock on method Display-1(): " + Thread.currentThread().getName());
        //
        //        System.out.println("Display-1(): " + Thread.currentThread().getName() + " : " + l.getHoldCount());
        //        System.out.println("Display-1(): " + Thread.currentThread().getName() + " : " + l.getQueueLength());
        //        Thread.sleep(10000);
        //        display2();
        //        System.out.println("Removing lock on method Display-1(): " + Thread.currentThread().getName());
        sum();

    }

    void sum()
    {
        l.lock();
        System.out.println("entering sum: " + Thread.currentThread().getName());
        System.out.println("sum(): " + Thread.currentThread().getName() + " : " + l.getHoldCount());
        System.out.println("sum(): " + Thread.currentThread().getName() + " : " + l.getQueueLength());
        System.out.println("Removing lock on method Sum(): " + Thread.currentThread().getName());
        l.unlock();
        System.out.println("Removing lock on method Display-1(): " + Thread.currentThread().getName());
        l.unlock();
    }


    void display2()
    {
        System.out.println("Taking lock on method Display-2(): " + Thread.currentThread().getName());
        l.lock();
        System.out.println("Display-2(): " + Thread.currentThread().getName() + " : " + l.getHoldCount());
        System.out.println("Display-2(): " + Thread.currentThread().getName() + " : " + l.getQueueLength());
        System.out.println("Removing lock on method Display-2(): " + Thread.currentThread().getName());
        l.unlock();
        System.out.println("Removing lock on method Display-1(): " + Thread.currentThread().getName());
        l.unlock();
    }


}

public class ReentrantLock2
{

    public static void main(String[] args)
    {
        //                ReentrantLock l = new ReentrantLock();
        //
        //                l.lock();
        //                l.lock();
        //
        //                System.out.println(l.getHoldCount()); // 2
        //                System.out.println(l.isLocked()); // true
        //                System.out.println(l.isHeldByCurrentThread()); // true
        //                System.out.println(l.getQueueLength()); // 0
        //
        //                l.unlock();
        //
        //                System.out.println(l.getHoldCount()); // 1
        //                System.out.println(l.isLocked()); // true
        //
        //                l.unlock();
        //
        //                System.out.println(l.isLocked()); // false
        //                System.out.println(l.isFair()); // false

        Rentrent rt = new Rentrent();
        Rentrent rt2 = new Rentrent();
        Thread th1 = new Thread(() -> {
            try
            {
                rt.display1();
            } catch(Exception e)
            {
            }
        });

        Thread th2 = new Thread(() -> {
            try
            {
                rt.sum();
            } catch(Exception e)
            {
            }
        });

        //        Thread th3 = new Thread(() -> {
        //            try
        //            {
        //                rt.sum();
        //            } catch(Exception e)
        //            {
        //            }
        //        });


        th1.start();
        th2.start();


    }
}
