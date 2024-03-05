package PUBSUB;

import org.zeromq.*;

public class Publisher
{

    public static void main(String[] args)
    {
        try(ZContext context = new ZContext())
        {
            // Create a PUB socket
            ZMQ.Socket publisher = context.createSocket(SocketType.PUB);
            // Bind the socket to a port
            publisher.bind("tcp://*:5556");

            while(!Thread.currentThread().isInterrupted())
            {
                // Send a message
                String message = "New message from publisher!";
                publisher.send(message.getBytes(ZMQ.CHARSET), 0);

                // Wait for a while before sending the next message
                Thread.sleep(1000);

            }
        } catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
