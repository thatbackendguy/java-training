package Multithreading.ThreadsDemo;

public class ThreadExample5
{
    public static void main(String[] args)
    {
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();

            System.out.println(threadName + " running");

        };

        Thread thread1 = new Thread(runnable);

        thread1.start();

        Thread thread2 = new Thread(runnable);

        thread2.start();
    }
}
