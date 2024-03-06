package MultiPubSubv2;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Subscriber {
    public static void main(String[] args) {
        try (ZContext context = new ZContext()) {
            // Create subscriber socket
            ZMQ.Socket sub = context.createSocket(SocketType.SUB);
            sub.subscribe("".getBytes());

            // Create 3 push sockets for sending acknowledgments
            ZMQ.Socket push1 = context.createSocket(SocketType.PUSH);
//            ZMQ.Socket push2 = context.createSocket(SocketType.PUSH);
//            ZMQ.Socket push3 = context.createSocket(SocketType.PUSH);

            // Connect subscriber socket
            sub.connect("tcp://localhost:5556");
            sub.connect("tcp://localhost:5557");
            sub.connect("tcp://localhost:5558");

            // Connect push sockets
            push1.connect("tcp://localhost:5560");
            push1.connect("tcp://localhost:5561");
            push1.connect("tcp://localhost:5562");

            while (!Thread.currentThread().isInterrupted()) {
                // Receive messages from publisher sockets
                byte[] message = sub.recv(0);
                String msg = new String(message, ZMQ.CHARSET);
                System.out.println("Received: " + msg);

                // Send acknowledgments using push sockets
                push1.send("Received at PUB".getBytes(), 0);
//                push1.send("Received at PUB2".getBytes(), 0);
//                push1.send("Received at PUB3".getBytes(), 0);
            }
        }
    }
}