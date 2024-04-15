package core.vertxinaction.chapter2verticles.deploy;

import io.vertx.core.Vertx;

public class Main
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle("chapter2.deploy.Deployer");
    }
}