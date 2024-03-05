package PUBSUB;

import org.zeromq.*;

public class Subscriber
{

    public static void main(String[] args)
    {
        try(ZContext context = new ZContext())
        {
            // Create a SUB socket
            ZMQ.Socket subscriber = context.createSocket(SocketType.SUB);
            // Connect to the publisher
            subscriber.connect("tcp://localhost:5556");
            // Subscribe to all messages (empty filter)
            subscriber.subscribe("".getBytes(ZMQ.CHARSET));

            while(!Thread.currentThread().isInterrupted())
            {
                // Receive a message
                byte[] message = subscriber.recv(0);
                System.out.println("Received message: " + new String(message, ZMQ.CHARSET));
            }
        }
    }
}
