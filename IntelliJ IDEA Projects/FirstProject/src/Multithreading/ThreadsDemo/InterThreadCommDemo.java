package Multithreading.ThreadsDemo;

class ThreadB extends Thread
{
    int total = 0;

    public void run()
    {
        synchronized(this)
        {
            System.out.println("Child thread starts calc...");

            for(int i = 0; i <= 100; i++)
            {
                total += i;
            }

            System.out.println("Child thread trying to give notification");

            this.notify();
        }
    }
}

public class InterThreadCommDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        ThreadB b = new ThreadB();

        b.start();

        synchronized(b)
        {
            System.out.println("Main thread trying to call wait() method");

            b.wait();

            System.out.println("Main thread got the notification");

            System.out.println(b.total);
        }

    }
}
