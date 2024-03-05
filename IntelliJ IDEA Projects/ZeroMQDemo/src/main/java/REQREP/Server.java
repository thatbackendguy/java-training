package REQREP;

import org.zeromq.*;

public class Server
{

    public static void main(String[] args)
    {
        try(ZContext context = new ZContext())
        {
            // Create a REP socket
            ZMQ.Socket socket = context.createSocket(SocketType.REP);
            // Bind the socket to a port
            socket.bind("tcp://*:5555");

            while(!Thread.currentThread().isInterrupted())
            {
                // Receive a request from the client
                byte[] request = socket.recv(0);
                System.out.println("Received request: " + new String(request, ZMQ.CHARSET));

                // Process the request (simulated here with a sleep)
                try
                {
                    Thread.sleep(1000);
                } catch(InterruptedException e)
                {
                    e.printStackTrace();
                }

                // Send a reply to the client
                String reply = "Hello from the server!";
                socket.send(reply.getBytes(ZMQ.CHARSET), 0);
            }
        }
    }
}