package ExtraTasks;

import java.util.concurrent.*;

public class CollectingDataFromDifferentFiles
{
    public static void main(String[] args) throws Exception
    {
        CountDownLatch count = new CountDownLatch(2);
        Runnable mergeJob = () -> {
            System.out.println("\n");
            System.out.println("Merging Data Into A Final File");
            System.out.println("Task Performed By:" + Thread.currentThread().getName() + "\n\n");
            count.countDown();
        };
        CyclicBarrier firstFiveFileBarrier = new CyclicBarrier(3, mergeJob);
        CyclicBarrier endBarrier = new CyclicBarrier(3, mergeJob);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(() -> {
            try
            {
                for(int i = 1; i <= 10; i++)
                {
                    System.out.println(Thread.currentThread().getName() + " Getting Data From File:" + i);
                    if(i == 5)
                    {
                        Thread.currentThread().interrupt();
                        firstFiveFileBarrier.await();
                    }
                }

                endBarrier.await();
            } catch(InterruptedException | BrokenBarrierException exception)
            {
                executorService.shutdownNow();
                System.out.println(Thread.currentThread().getName() + "...." + exception.getClass().getName());
            }
        });
        executorService.submit(() -> {
            try
            {
                for(int i = 11; i <= 20; i++)
                {
                    System.out.println(Thread.currentThread().getName() + " Getting Data From File:" + i);
                    if(i == 15)
                    {
                        firstFiveFileBarrier.await();
                    }
                }
                endBarrier.await();
            } catch(InterruptedException | BrokenBarrierException exception)
            {
                executorService.shutdownNow();
                System.out.println(Thread.currentThread().getName() + "...." + exception.getClass().getName());
            }
        });
        executorService.submit(() -> {
            try
            {
                for(int i = 21; i <= 30; i++)
                {
                    System.out.println(Thread.currentThread().getName() + " Getting Data From File:" + i);
                    if(i == 25)
                    {
                        firstFiveFileBarrier.await();
                    }
                }
                endBarrier.await();
            } catch(InterruptedException | BrokenBarrierException exception)
            {
                executorService.shutdownNow();
                System.out.println(Thread.currentThread().getName() + "...." + exception.getClass().getName());
            }
        });

        //        count.await();
        executorService.shutdown();
    }
}