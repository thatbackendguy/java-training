package Multithreading.ThreadsDemo;

public class ThreadExample2
{

    // use Runnable interface
    public static class MyRunnable implements Runnable
    {
        @Override
        public void run()
        {
            System.out.println("MyRunnable running");

            System.out.println("MyRunnable running");
        }
    }

    public static void main(String[] args)
    {

        Thread thread = new Thread(new MyRunnable());

        thread.start();
    }

}
