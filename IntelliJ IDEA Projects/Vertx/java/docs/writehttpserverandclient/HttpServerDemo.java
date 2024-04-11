package docs.writehttpserverandclient;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class HttpServerDemo extends AbstractVerticle
{

    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HttpServerDemo());
    }

    @Override
    public void start()
    {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        // Define routes
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.end("<h1>Hello from the <i>home route!</i></h1>");
        });

        router.route("/about").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.end("<h1>This is the about page.</h1>");
        });

        router.route("/users/:userId/:start/:end").handler(routingContext -> {
            HttpServerRequest request = routingContext.request();
            long offset = 0;
            try
            {
                offset = Long.parseLong(request.getParam("start"));
            } catch(NumberFormatException e)
            {
                System.out.println(e);
            }

            long end = Long.MAX_VALUE;
            try
            {
                end = Long.parseLong(request.getParam("end"));
            } catch(NumberFormatException e)
            {
                System.out.println(e);
            }

            String userId = request.getParam("userId");

            HttpServerResponse response = routingContext.response();
            response.sendFile("/home/yash/video.mp4");
            response.end("<p>User ID: <i> " + userId + "</i></p>");
        });

        // Mount the router
        server.requestHandler(router).listen(8080, res -> {
            if(res.succeeded())
            {
                System.out.println("Server is now listening on http://localhost:8080/");
            }
            else
            {
                System.out.println("Failed to start the server");
            }
        });
    }
}