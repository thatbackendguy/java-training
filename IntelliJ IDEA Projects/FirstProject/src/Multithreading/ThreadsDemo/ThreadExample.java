package Multithreading.ThreadsDemo;

public class ThreadExample
{
    public static class MyThread extends Thread // not recommended
    {
        public void run()
        {

            System.out.println("MyThread running");

            System.out.println("MyThread running");
        }
    }

    public static void main(String[] args)
    {

        MyThread myThread = new MyThread();

        myThread.start();
    }

}
