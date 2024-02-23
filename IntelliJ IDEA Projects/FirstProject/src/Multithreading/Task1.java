package Multithreading;

public class Task1
{
    public static void main(String[] args) throws Exception
    {
        Runnable runnable = new Runnable()
        {
            int num = 1;

            @Override
            public void run()
            {
                System.out.println(Thread.currentThread().getName() + " : " + num);
                num++;
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

        t1.start();
        t1.join();
        t2.start();
    }
}