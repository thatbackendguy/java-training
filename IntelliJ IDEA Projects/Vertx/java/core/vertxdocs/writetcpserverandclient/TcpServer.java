package vertxdocs.writetcpserverandclient;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;

public class TcpServer {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        NetServerOptions options = new NetServerOptions().setPort(1234);

        NetServer server = vertx.createNetServer(options);

        server.connectHandler(socket -> {
            socket.handler(buffer -> {
                System.out.println("Received data: " + buffer.toString());

                // Write back to the client
                socket.write("Hello from server!");
            });
        });

        server.listen(res -> {
            if (res.succeeded()) {
                System.out.println("Server is now listening!");
            } else {
                System.out.println("Failed to bind!");
            }
        });
    }
}