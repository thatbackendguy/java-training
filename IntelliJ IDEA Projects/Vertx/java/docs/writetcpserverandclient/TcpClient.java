package docs.writetcpserverandclient;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetClientOptions;
import io.vertx.core.net.NetSocket;

public class TcpClient {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        NetClientOptions options = new NetClientOptions().setConnectTimeout(10000);

        NetClient client = vertx.createNetClient(options);

        client.connect(1234, "localhost", res -> {
            if (res.succeeded()) {
                System.out.println("Connected!");
                NetSocket socket = res.result();

                // Write data to the server
                socket.write(Buffer.buffer("Hello from client!"));

                // Handle data from the server
                socket.handler(buffer -> {
                    System.out.println("Received data: " + buffer.toString());
                });
            } else {
                System.out.println("Failed to connect: " + res.cause().getMessage());
            }
        });
    }
}