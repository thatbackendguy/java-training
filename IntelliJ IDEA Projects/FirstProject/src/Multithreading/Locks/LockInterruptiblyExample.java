package Multithreading.Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyExample
{
    private Lock lock = new ReentrantLock();

    public void exampleMethod()
    {
        try
        {
            // Attempt to acquire the lock
            lock.lockInterruptibly();

            try
            {
                // Critical section - Shared resource access
                System.out.println("Critical section is executing. => " + Thread.currentThread().getName());
                Thread.sleep(5000); // Simulate some work
                System.out.println("Critical section is finished.");
            } finally
            {
                // Release the lock
                System.out.println("finally executed");
                lock.unlock();
            }
        } catch(InterruptedException e)
        {
            // Handle interruption while waiting for the lock
            System.out.println("Lock acquisition was interrupted.");
        }
    }

    public static void main(String[] args)
    {
        LockInterruptiblyExample example = new LockInterruptiblyExample();
        Thread thread = new Thread(() -> {
            // Acquire the lock from a separate thread
            example.exampleMethod();
        });
        Thread thread2 = new Thread(() -> {
            // Acquire the lock from a separate thread
            example.exampleMethod();
        });

        thread.start();
        thread2.start();
        // Interrupt the waiting thread after 2 seconds
        try
        {
            Thread.sleep(2000);
            thread.interrupt();
        } catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
