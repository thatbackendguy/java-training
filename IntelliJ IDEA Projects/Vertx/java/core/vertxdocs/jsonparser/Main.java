package core.vertxdocs.jsonparser;

import io.vertx.core.Vertx;

public class Main
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle("core.vertxdocs.jsonparser.POJOSenderVerticle");

        vertx.deployVerticle("core.vertxdocs.jsonparser.POJORecvVerticle");
    }
}
