package Multithreading.ThreadsDemo;

class ThreadDemo extends Thread
{
    public void run()
    {
        System.out.println("Inside run method");
    }

    public static void main(String[] args)
    {
        // Setting priority for main thread
        Thread.currentThread().setPriority(7);

        // Printing main thread priority
        System.out.println("Main thread priority: " + Thread.currentThread().getPriority());

        // Creating child thread
        ThreadDemo childThread = new ThreadDemo();

        // Printing child thread priority
        System.out.println("Child thread priority: " + childThread.getPriority());    //7
    }
}