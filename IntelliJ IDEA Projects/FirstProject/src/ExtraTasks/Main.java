package ExtraTasks;

class ThreadA extends Thread
{
    public void start()
    {
        System.out.println("Thread-A class start() called: " + Thread.currentThread().getName());
    }

    public void run()
    {
        System.out.println("Thread-A class run() called: " + Thread.currentThread().getName());
    }
}

public class Main
{

    public static void main(String[] args)
    {

        ThreadA t = new ThreadA();
        t.start();
    }
}