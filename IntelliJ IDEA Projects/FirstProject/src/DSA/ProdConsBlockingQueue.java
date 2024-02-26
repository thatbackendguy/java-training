package DSA;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Producer implements Runnable
{
    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue)
    {
        this.queue = queue;
    }

    public void run()
    {
        try
        {
            for(int i = 1; i <= 1000; i++)
            {
                queue.put(i);
                System.out.println("Produced: " + i);
                //                Thread.sleep(1000);
            }
        } catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}

class Consumer implements Runnable
{
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue)
    {
        this.queue = queue;
    }

    public void run()
    {
        try
        {
            while(true)
            {
                Integer value = queue.take();
                System.out.println("Consumed: " + value);
            }
        } catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
    }
}

public class ProdConsBlockingQueue
{
    public static void main(String[] args)
    {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }
}
