package web.sockjs;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SockJsEcho extends AbstractVerticle
{
    private static Logger LOGGER = LoggerFactory.getLogger(SockJsEcho.class);

    public static void main(String[] args)
    {
        Vertx.vertx().deployVerticle("web.sockjs.ServerEcho").onSuccess(res -> LOGGER.info("Verticle deployed successfully!"));
    }

    @Override
    public void start() throws Exception
    {
        var router = Router.router(vertx);

        var sockJSHandler = SockJSHandler.create(vertx, new SockJSHandlerOptions().setHeartbeatInterval(2000).setRegisterWriteHandler(true));

        router.route("/sockjs/*").subRouter(sockJSHandler.socketHandler(sockJSSocket -> {
            sockJSSocket.handler(buffer -> {

                // Retrieve the writeHandlerID and store it (e.g. in a local map)
                String writeHandlerID = sockJSSocket.writeHandlerID();

                //read and modify
                var newMessage = buffer.toString() + " [" + writeHandlerID + "]";

                System.out.println("Received message from client: " + buffer);

                //send message
                sockJSSocket.write(newMessage);
            });
        }));

        vertx.createHttpServer(new HttpServerOptions().setHost("10.20.40.227")).requestHandler(router).connectionHandler(httpConnection -> {
            LOGGER.info("HTTP connection established - {}", httpConnection.remoteAddress());
        }).listen(8080).onComplete(httpServerAsyncResult -> {
            if(httpServerAsyncResult.succeeded())
            {
                LOGGER.info("HTTP server started on port 8080");
            }
        });
    }
}