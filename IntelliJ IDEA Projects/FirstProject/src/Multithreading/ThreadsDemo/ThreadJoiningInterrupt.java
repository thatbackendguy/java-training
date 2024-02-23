package Multithreading.ThreadsDemo;

public class ThreadJoiningInterrupt
{

    public static void main(String[] args)
    {

        ChildThread childThread = new ChildThread(Thread.currentThread());

        childThread.start();

        try
        {
            childThread.join();   //Joined
        } catch(InterruptedException ie)
        {
            System.out.println("main Thread is interrupted");

        }

        for(int i = 1; i <= 4; i++)
        {
            System.out.println("main Thread Execution - " + i);
        }

        Thread thread1 = new Thread();
        Thread thread2 = new Thread();
        Thread thread3 = new Thread();

        // Getting Priorities of above threads
        System.out.println("\n----------------------\nThread1 priority : " + thread1.getPriority());   // Print ‘5’ as default priority
        System.out.println("Thread2 priority : " + thread2.getPriority());   // Print ‘5’ as default priority
        System.out.println("Thread3 priority : " + thread3.getPriority());     // Print ‘5’ as default priority

        // Setting priority for above threads
        thread1.setPriority(4);

        // Setting Priority of 1 using Thread constant for minimum priority
        thread2.setPriority(Thread.MIN_PRIORITY);

        // thread3.setPriority(15);  // This will throw IllegalArgumentException

        // Again getting Priorities for above Threads
        System.out.println("Thread1 new priority : " + thread1.getPriority());   // Print ‘4’ as new priority
        System.out.println("Thread2 new priority : " + thread2.getPriority());
    }
}

class ChildThread extends Thread
{

    private static Thread parentThreadRef;

    public ChildThread(Thread parentThreadRef)
    {
        this.parentThreadRef = parentThreadRef;
    }

    public void run()
    {

        parentThreadRef.interrupt();   //Interrupted

        for(int i = 1; i <= 4; i++)
        {
            System.out.println("Child Thread Execution - " + i);
        }
    }
}