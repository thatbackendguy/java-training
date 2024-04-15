package web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import core.vertxdocs.jsonparser.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoutingDemo extends AbstractVerticle
{
    private Logger logger = LoggerFactory.getLogger(RoutingDemo.class);

    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new RoutingDemo());
    }

    @Override
    public void start()
    {
        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route(HttpMethod.GET, "/").blockingHandler(ctx -> {
            logger.info("{} {} {}", ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());
            try
            {
                Thread.sleep(5000);
            } catch(InterruptedException e)
            {
                System.out.println(e.getMessage());
            }
            ctx.next();
        });

        router.route(HttpMethod.GET, "/").handler(ctx -> {
            logger.info("{} {} {}", ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            ctx.response().end("Hello World");
        });

        router.get("/get/hello-world").respond(ctx -> Future.succeededFuture(new JsonObject().put("message", "Hello World")));

        router.route("/get/pojo").handler(ctx -> {
            HttpServerResponse response = ctx.response();

            logger.info("{} {} {}", ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            User user = new User("yash", "prajapati", 22, "yash@gmail.com");

            JsonObject json = new JsonObject(String.valueOf(user));

            response.send(json.toString());
        });

        router.route(HttpMethod.GET, "/get/sample-video").handler(ctx -> {
            logger.info("{} {} {}", ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            ctx.response().setChunked(true).sendFile("/home/yash/video.mp4");
        });

        router.route(HttpMethod.GET, "/get/ftp-server").handler(ctx -> {
            logger.info("{} {} {}", ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            ctx.response().setChunked(true).sendFile("/home/yash/ftp-server.txt");
        });

        router.route(HttpMethod.POST,"/form-data").handler(ctx -> {
            HttpServerRequest req = ctx.request();

            logger.info("{} {} {}", ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            req.setExpectMultipart(true);

            req.uploadHandler(upload -> {
                upload.handler(buffer -> {
                    System.out.println("Received file data" + buffer.toString());
                });

                upload.endHandler(v -> {
                    System.out.println("File upload completed");
                });
            });

            req.endHandler(v -> {
                MultiMap formAttributes = req.formAttributes();

                String name = formAttributes.get("name");

                System.out.println("Name: " + name);

                JsonObject res = new JsonObject().put("status", "success").put("message", "Form submitted successfully!").put("name", name);

                ctx.response().setStatusCode(201).putHeader("Content-Type", "application/json").end(res.toBuffer());
            });
        });

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
