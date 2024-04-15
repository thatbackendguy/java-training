package core.vertxinaction.chapter2verticles.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloVerticle extends AbstractVerticle
{
    private final Logger logger = LoggerFactory.getLogger(HelloVerticle.class);

    @Override
    public void start()
    {
        vertx.setPeriodic(5000, id -> {
            logger.info("tick");
        });

        vertx.createHttpServer().requestHandler(req -> {
            logger.info("request: {} {}", req.method(), req.path());
            req.response().end("Hello World!");

        }).listen(8080);

        logger.info("Server open at localhost:8080");
    }

    @Override
    public void stop()
    {
        logger.info("Stopping verticle");
    }

    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle("chapter2.hello.HelloVerticle", res -> {
            var deploymentId = res.result();


            vertx.setTimer(10000, e -> {
                System.out.println("preparing to undeploy");
                vertx.undeploy(deploymentId);
            });

        });

    }
}
