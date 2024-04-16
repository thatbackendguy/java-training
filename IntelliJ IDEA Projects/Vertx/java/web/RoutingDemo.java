package web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.MultiMap;
import io.vertx.core.Vertx;
import io.vertx.core.http.*;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import core.vertxdocs.jsonparser.User;
import io.vertx.ext.web.handler.ResponseContentTypeHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.TimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoutingDemo extends AbstractVerticle
{
    private Logger logger = LoggerFactory.getLogger(RoutingDemo.class);

    private String reqContainer = "{} {} {}";

    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new RoutingDemo());
    }

    @Override
    public void start()
    {
        HttpServer server = vertx.createHttpServer(new HttpServerOptions().setHost("10.20.40.227").setPort(8080));

        Router mainRouter = Router.router(vertx);

        Router searchEngineRouter = Router.router(vertx);

        mainRouter.route().handler(ResponseContentTypeHandler.create()).handler(TimeoutHandler.create(5000));

        mainRouter.route("/*").handler(StaticHandler.create("static").setIndexPage("index.html").setCachingEnabled(false));

        mainRouter.route("/static/*").handler(StaticHandler.create("static").setDirectoryListing(true).setDirectoryTemplate("/home/yash/IdeaProjects/vertx-practise/static/template.html").setIncludeHidden(true).setCachingEnabled(false));

        // binding sub-router to Main router
        mainRouter.route("/search/*").subRouter(searchEngineRouter);

        // http://localhost:8080/timeout
        searchEngineRouter.route(HttpMethod.GET, "/timeout").blockingHandler(ctx -> {
            logger.info(reqContainer, ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());
            try
            {
                Thread.sleep(10000);
            } catch(InterruptedException e)
            {
                logger.error("Interrupted!", e);
                Thread.currentThread().interrupt();
            }
            ctx.redirect("/");
        });

        // http://localhost:8080/
        mainRouter.route(HttpMethod.GET, "/").handler(ctx -> {
            logger.info(reqContainer, ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            ctx.response().addCookie(Cookie.cookie("isVisited", "true")).end("Hello World");
        });

        // http://localhost:8080/get/hello-world
        mainRouter.get("/get/hello-world").respond(ctx -> Future.succeededFuture(new JsonObject().put("message", "Hello World")));

        // http://localhost:8080/get/pojo
        mainRouter.route("/get/pojo").handler(ctx -> {
            HttpServerResponse response = ctx.response();

            logger.info(reqContainer, ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            User user = new User("yash", "prajapati", 22, "yash@gmail.com");

//            JsonObject json = new JsonObject(String.valueOf(user));

//            response.send(json.toString());
            response.end(user.getFirstName());
        });

        // http://localhost:8080/get/sample-video
        mainRouter.route(HttpMethod.GET, "/get/sample-video").handler(ctx -> {
            logger.info(reqContainer, ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            ctx.response().setChunked(true).sendFile("/home/yash/video.mp4");
        });

        // http://localhost:8080/get/ftp-server
        mainRouter.route(HttpMethod.GET, "/get/ftp-server").handler(ctx -> {
            logger.info(reqContainer, ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            ctx.response().setChunked(true).sendFile("/home/yash/ftp-server.txt");
        });

        // http://localhost:8080/form-data
        mainRouter.route(HttpMethod.POST, "/form-data").handler(ctx -> {
            HttpServerRequest req = ctx.request();

            logger.info(reqContainer, ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            req.setExpectMultipart(true);

            req.uploadHandler(upload -> {
                upload.handler(buffer -> System.out.println("Received file data" + buffer.toString()));

                upload.endHandler(v -> System.out.println("File upload completed"));
            });

            req.endHandler(v -> {
                MultiMap formAttributes = req.formAttributes();

                String name = formAttributes.get("name");

                System.out.println("Name: " + name);

                JsonObject res = new JsonObject().put("status", "success").put("message", "Form submitted successfully!").put("name", name);

                ctx.response().setStatusCode(201).putHeader("Content-Type", "application/json").end(res.toBuffer());
            });
        });

        // capturing path parameters
        // http://localhost:8080/user/John Doe/1234567890/10-20
        mainRouter.route(HttpMethod.GET, "/user/:name/:phone/:from-:to").produces("application/json").handler(ctx -> {
            logger.info(reqContainer, ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            var name = ctx.pathParam("name");

            var phone = ctx.pathParam("phone");

            var from = ctx.pathParam("from");

            var to = ctx.pathParam("to");

            ctx.json(new JsonObject().put("name", name).put("phone", phone).put("duration", from + " to " + to));
        });

        // routing order
        // http://localhost:8080/routing-order
        mainRouter.route(HttpMethod.GET, "/routing-order").order(0).handler(ctx -> {
            logger.info(reqContainer, ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            HttpServerResponse response = ctx.response();

            response.setChunked(true);

            response.write("Ths is route 1: setChunked(true)\n");

            ctx.next();
        });

        // http://localhost:8080/routing-order
        mainRouter.route(HttpMethod.GET, "/routing-order").order(2).handler(ctx -> {
            logger.info(reqContainer, ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            HttpServerResponse response = ctx.response();

            response.write("Ths is route 2\n");

            ctx.next();
        });

        // http://localhost:8080/routing-order
        mainRouter.route(HttpMethod.GET, "/routing-order").order(1).handler(ctx -> {
            logger.info(reqContainer, ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            HttpServerResponse response = ctx.response();

            response.write("Ths is route 3\n");

            ctx.next();
        });

        // http://localhost:8080/routing-order
        mainRouter.route(HttpMethod.GET, "/routing-order").order(3).handler(ctx -> {
            logger.info(reqContainer, ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            HttpServerResponse response = ctx.response();

            response.write("Ths is route 4: ending ctx.response()\n");

            ctx.response().end();
        });

        // metadata
        // http://localhost:8080/metadata/mongodb
        mainRouter.route(HttpMethod.GET, "/metadata/mongodb").putMetadata("db-uri", "mongodb://localhost:27017/testDB").putMetadata("db-user", "admin").putMetadata("db-pass", "admin123").handler(ctx -> {
            Route route = ctx.currentRoute();

            String dbUri = route.getMetadata("db-uri");

            String dbUser = route.getMetadata("db-user");

            String dbPass = route.getMetadata("db-pass");

            ctx.json(new JsonObject().put("db-uri", dbUri).put("db-user", dbUser).put("db-pass", dbPass).put("cookie", new JsonObject().put("isVisited", ctx.request().getCookie("isVisited").getValue())));
        });

        // helper function
        // http://localhost:8080/download/pdf
        mainRouter.get("/download/pdf").handler(ctx -> {
            vertx.fileSystem().readFile("/home/yash/Downloads/Redhat9Certificate.pdf", res -> {
                if(res.succeeded())
                {
                    ctx.attachment("Redhat9Certificate.pdf").response().putHeader("Content-Type", "application/pdf").end(res.result());
                }
                else
                {
                    ctx.response().end(res.cause().getMessage());
                }
            });
        });

        // YouTube search redirect
        // http://localhost:8080/search/yt/YashPrajapati
        searchEngineRouter.route(HttpMethod.GET, "/yt/:query").handler(ctx -> {
            logger.info(reqContainer, ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            var query = ctx.pathParam("query");

            ctx.redirect(String.format("https://www.youtube.com/results?search_query=%s", query));
        });

        // Google search redirect
        // http://localhost:8080/search/google/thatbackendguy
        searchEngineRouter.route(HttpMethod.GET, "/google/:query").handler(ctx -> {
            logger.info(reqContainer, ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            var query = ctx.pathParam("query");

            ctx.redirect(String.format("https://www.google.com/search?q=%s", query));
        });

        // re-routing
        // http://localhost:8080/api/path
        mainRouter.get("/api/path").handler(ctx -> {
            logger.info(reqContainer + " 1", ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            ctx.put("name", "yash");

            ctx.next();

        });

        mainRouter.get("/api/path/B").handler(ctx -> {
            logger.info(reqContainer, ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            ctx.response().end();
        });

        mainRouter.get("/api/path").handler(ctx -> {
            logger.info(reqContainer + " 2", ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());
            ctx.reroute("/api/path/B");
        });

        // http://localhost:8080/gen-error
        mainRouter.get("/gen-error").handler(ctx -> {
            logger.info(reqContainer, ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            throw new RuntimeException("something happened!");
        });

        // error handling
        Route route = mainRouter.get("/gen-error");

        route.failureHandler(ctx -> ctx.json(new JsonObject().put("status", "error")));

        // echo json object
        mainRouter.route(HttpMethod.POST, "/echo-json").handler(ctx -> {
            logger.info(reqContainer, ctx.request().method(), ctx.request().path(), ctx.request().remoteAddress());

            ctx.request().bodyHandler(buffer -> {
                ctx.json(buffer.toJsonObject());
            });
        });

        // server starting
        server.requestHandler(mainRouter).listen(8080, res -> {
            if(res.succeeded())
            {

                System.out.println("Server is now listening on http://10.20.40.227:8080/");
            }
            else
            {
                System.out.println("Failed to start the server");
            }
        });
    }
}
