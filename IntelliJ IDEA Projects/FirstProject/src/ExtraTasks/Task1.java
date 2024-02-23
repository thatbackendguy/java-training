package ExtraTasks;

class PrintOutput
{
    public void printA() throws InterruptedException
    {
        int counterOnce = 0;
        while(true)
        {
            synchronized(this)
            {
                if(counterOnce == 0)
                {
                    System.out.println(Thread.currentThread().getName() + " : " + counterOnce++);
                }

                wait();

                System.out.println(Thread.currentThread().getName() + " : " + counterOnce++);

                notify();
            }
        }
    }

    public void printB() throws InterruptedException
    {
        int counterTwice = 0;
        while(true)
        {
            synchronized(this)
            {
                System.out.println(Thread.currentThread().getName() + " : " + counterTwice++);
                if(counterTwice % 2 == 0)
                {
                    notify();
                    wait();
                }
            }

        }


    }
}

public class Task1
{
    public static void main(String[] args)
    {
        PrintOutput obj = new PrintOutput();

        Thread th1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    obj.printA();
                } catch(InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread th2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    obj.printB();
                } catch(InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });

        th1.start();
        th2.start();
    }
}