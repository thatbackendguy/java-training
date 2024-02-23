package DSA;

import java.util.concurrent.CopyOnWriteArrayList;


public class Practise1
{
    public static void main(String[] args) throws InterruptedException
    {
        CopyOnWriteArrayList<Integer> cow = new CopyOnWriteArrayList<>();

        CopyOnWriteArrayList<Integer> toDelete = new CopyOnWriteArrayList<>();

        Thread th1 = new Thread(new Runnable()
        {
            @Override
            public void run()

            {
                for(int i = 1; i <= 1000; i++)
                {
                    cow.add(i);

                    if(i % 2 == 0)
                    {
                        toDelete.add(i);
                    }
                    try
                    {
                        Thread.sleep(10);
                    } catch(InterruptedException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread th2 = new Thread(new Runnable()
        {
            @Override
            public void run()

            {

                while(true)
                {
                    System.out.println(cow);

                    try
                    {
                        Thread.sleep(10);
                    } catch(InterruptedException e)
                    {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        Thread th3 = new Thread(new Runnable()
        {
            @Override
            public void run()

            {
                while(true)
                {
                    try
                    {
                        Thread.sleep(10);

                        cow.removeAll(toDelete);

                    } catch(InterruptedException e)
                    {
                        throw new RuntimeException(e);
                    }

                }
            }
        });


        th1.start();

        th2.start();

        th3.start();

        //th.join();
    }
}
