package MultiPubSubv2;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

public class Publisher {
    public static void main(String[] args) throws Exception {
        try (ZContext context = new ZContext()) {
            // Create 3 publisher sockets
            ZMQ.Socket pub1 = context.createSocket(SocketType.PUB);
//            ZMQ.Socket pub2 = context.createSocket(SocketType.PUB);
//            ZMQ.Socket pub3 = context.createSocket(SocketType.PUB);

            // Create 3 pull sockets for receiving acknowledgments
            ZMQ.Socket pull1 = context.createSocket(SocketType.PULL);
//            ZMQ.Socket pull2 = context.createSocket(SocketType.PULL);
//            ZMQ.Socket pull3 = context.createSocket(SocketType.PULL);

            // Bind publisher sockets
            pub1.bind("tcp://*:5556");
            pub1.bind("tcp://*:5557");
            pub1.bind("tcp://*:5558");

            // Bind pull sockets
            pull1.bind("tcp://*:5560");
            pull1.bind("tcp://*:5561");
            pull1.bind("tcp://*:5562");

            // Add a delay or sleep before sending messages
            Thread.sleep(2000); // Delay of 2 seconds

            // Send messages from publisher sockets
            for (int i = 0; i < 1000; i++) {
                pub1.send("Message " + i + " from pub1");
                byte[] pull1Ack = pull1.recv(0);
                String msg1 = new String(pull1Ack);
                System.out.println(msg1);

//                if(msg1.equals("Received at PUB"))
//                {
//                    pub1.send("Message " + i + " from pub2");
//                    byte[] pull2Ack = pull1.recv(0);
//                    String msg2 = new String(pull2Ack);
//                    System.out.println(msg2);
//
//                    if(msg2.equals("Received at PUB"))
//                    {
//                        pub1.send("Message " + i + " from pub3");
//                        byte[] pull3Ack = pull1.recv(0);
//                        System.out.println(new String(pull3Ack));
//                    } else {
//                        System.out.println("Not received msg at PUB2");
//                    }
//                } else {
//                    System.out.println("Not received msg at PUB1");
//                }

                Thread.sleep(1000);
            }
        }
    }
}