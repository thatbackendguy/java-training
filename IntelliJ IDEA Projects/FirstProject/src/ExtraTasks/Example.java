package ExtraTasks;

class shivam
{
    static synchronized void m1() throws Exception
    {

        System.out.println("yum one - " + Thread.currentThread().getName());
        Thread.sleep(5000);
    }

    static synchronized void m2() throws Exception
    {
        System.out.println("yum two - " + Thread.currentThread().getName());
        Thread.sleep(5000);
    }

}

public class Example
{
    public static void main(String[] args)
    {
        shivam obj = new shivam();
        shivam obj2 = new shivam();
        Thread th1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    obj.m1();
                } catch(Exception e)
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
                    obj2.m2();
                } catch(Exception e)
                {
                    throw new RuntimeException(e);
                }
            }
        });

        th1.start();
        th2.start();


    }
}