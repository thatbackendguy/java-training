package Multithreading.ThreadsDemo;

public class ThreadExample6
{
    public static void main(String[] args)
    {
        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();

            System.out.println(threadName + " running...");

            try
            {
                Thread.sleep(2000);
            } catch(Exception e)
            {
                System.out.println(e);
            }

            System.out.println(threadName + " finished!");

        };

        Thread thread1 = new Thread(runnable);

        thread1.start();

    }
}
