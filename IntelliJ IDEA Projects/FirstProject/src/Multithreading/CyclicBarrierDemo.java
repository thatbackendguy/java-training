package Multithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo
{


    public static void main(String[] args)
    {

        Runnable barrierAction = new Runnable()
        {
            @Override
            public void run()
            {

                System.out.println("Barrier Action called");
            }
        };

        CyclicBarrier cb = new CyclicBarrier(3, barrierAction);

        Runnable runnable = new Runnable()
        {
            @Override
            public void run()
            {

                System.out.println("Runnable executed: " + Thread.currentThread().getName());
                try
                {
                    cb.await();
                } catch(InterruptedException e)
                {
                    throw new RuntimeException(e);
                } catch(BrokenBarrierException e)
                {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " exiting...");
            }

        };


        Thread t1 = new Thread(runnable);
        t1.start();

        Thread t2 = new Thread(runnable);
        t2.start();

        Thread t3 = new Thread(runnable);
        t3.start();

        System.out.println("Main thread exited");

        // System.out.println(Thread.currentThread().getThreadGroup().getName());


    }


}
