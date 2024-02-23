package Multithreading.ThreadsDemo;

public class ThreadExample3
{

    public static void main(String[] args)
    {
        Runnable runnable = new Runnable()
        {
            private int count = 0;

            @Override
            public void run()
            {
                for(int i = 0; i < 1000_000; i++)
                {
                    synchronized(this)
                    {
                        this.count++;
                    }
                }
                System.out.println(Thread.currentThread().getName() + ":" + this.count);
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();
    }

}
