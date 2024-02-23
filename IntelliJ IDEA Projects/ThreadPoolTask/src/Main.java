import java.util.*;

class MyThreadPool
{
    private final List<MyThread> threadsList;

    private final LinkedList<Runnable> tasksList;

    public MyThreadPool(int noOfThreads)
    {
        threadsList = new LinkedList<>();

        tasksList = new LinkedList<>();

        for(int i = 0; i < noOfThreads; i++)
        {
            MyThread th = new MyThread();

            th.start();

            threadsList.add(th);
        }
    }

    public void execute(Runnable task)
    {
        synchronized(tasksList)
        {
            tasksList.addLast(task);

            tasksList.notify();
        }
    }

    class MyThread extends Thread
    {
        @Override
        public void run()
        {
            Runnable task;

            while(true)
            {
                synchronized(tasksList)
                {
                    while(tasksList.isEmpty())
                    {
                        try
                        {
                            tasksList.wait();

                        }
                        catch(InterruptedException e)
                        {
                            Thread.currentThread().interrupt();

                            return;
                        }
                    }

                    task = tasksList.removeFirst();
                }

                try
                {
                    task.run();
                } catch(RuntimeException e)
                {
                    System.out.println(e);
                }
            }
        }
    }

    public void shutdown()
    {
        for(MyThread th : threadsList)
        {
            th.interrupt();
        }
    }

}

public class Main
{
    public static void main(String[] args)
    {
        MyThreadPool pool = new MyThreadPool(10);

        for(int i = 1; i <= 1000; i++)
        {
            int taskId = i;

            pool.execute(() -> {

                System.out.println("Task " + taskId + " executed by thread: " + Thread.currentThread().getName());
            });
        }

        pool.shutdown();
    }
}
