package REQREP;

import org.zeromq.*;

public class Client
{

    public static void main(String[] args)
    {
        try(ZContext context = new ZContext())
        {
            // Create a REQ socket
            ZMQ.Socket socket = context.createSocket(SocketType.REQ);
            // Connect the socket to the server
            socket.connect("tcp://localhost:5555");

            // Send a request to the server
            String message = "Hello from the client!";
            socket.send(message.getBytes(ZMQ.CHARSET), 0);

            // Receive the reply from the server
            byte[] reply = socket.recv(0);
            System.out.println("Received reply: " + new String(reply, ZMQ.CHARSET));
        }
    }
}
