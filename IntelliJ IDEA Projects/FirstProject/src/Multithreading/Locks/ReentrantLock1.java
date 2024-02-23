package Multithreading.Locks;

import java.util.concurrent.locks.ReentrantLock;

class Display
{
    ReentrantLock l = new ReentrantLock();

    public void wish(String name)
    {
        l.lock();

        for(int i = 0; i < 5; i++)
        {
            System.out.print("Good Morning: ");

            try
            {
                Thread.sleep(1000);
            } catch(InterruptedException e)
            {
            }

            System.out.println(name);
        }

        l.unlock();
    }
}

class MyThread extends Thread
{
    Display d;

    String name;

    MyThread(Display d, String name)
    {
        this.d = d;
        this.name = name;
    }

    public void run()
    {
        d.wish(name);
    }
}

public class ReentrantLock1
{
    public static void main(String[] args)
    {
        Display d = new Display();

        MyThread t1 = new MyThread(d, "John");
        MyThread t2 = new MyThread(d, "Alex");
        MyThread t3 = new MyThread(d, "Bob");

        t1.start();
        t2.start();
        t3.start();

    }
}
