package ExtraTasks;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Parent
{
    int x = 10;

    Parent()
    {
        System.out.println("Parent Cons");
    }
}

class Child extends Parent
{
    int y = 20;

    Child()
    {
        System.out.println("Child Cons");
        System.out.println(this.y);
        System.out.println(this.x);
    }


}


public class Demo1
{
    static int x = 1;


    public static void main(String[] args)
    {
        Integer i1 = Integer.valueOf(100);
        Integer i2 = Integer.valueOf(100);

        System.out.println(i1 == i2);
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getThreadGroup().getName());
        ExecutorService es = Executors.newFixedThreadPool(5);


        for(int i = 0; i < 5; i++)
        {
            es.submit(new Runnable()
            {
                @Override
                public void run()
                {

                    System.out.println(Thread.currentThread().getName());
                    System.out.println(Thread.currentThread().getThreadGroup().getName());
                }
            });
        }

        es.shutdown();

        int[] a = {1, 2, 3, 4, 5};
        int[] b = {9, 8};

        a = b;
        b = a;
        System.out.println(a[0] + ":" + b[0]);
        System.out.println(x);


        ArrayList<Integer> al = new ArrayList<>();

        try
        {
            while(true)
            {
                al.add(1);
            }
        } catch(RuntimeException r)
        {
            System.out.println("run");
        } catch(Exception e)
        {
            System.out.println("exception");
        }
        System.out.println("adf");
    }

    public void record()
    {
        x = 10;
        System.out.println(x);
    }
}