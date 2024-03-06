package PUSHPULL;

import org.zeromq.*;

public class Puller {

    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            // Create a PULL socket
            ZMQ.Socket puller = context.createSocket(SocketType.PULL);
            // Connect to the pusher (optional)
             puller.connect("tcp://localhost:3000"); // Uncomment if pusher runs on different machine
            // Bind the socket to a port (useful if puller runs on different machine)
//             puller.bind("tcp://*:3000");  // Uncomment if pusher binds

            while (!Thread.currentThread().isInterrupted()) {
                // Receive messages
                byte[] message = puller.recv(0);
                System.out.println("Received message: " + new String(message, ZMQ.CHARSET));
            }
        }
    }
}