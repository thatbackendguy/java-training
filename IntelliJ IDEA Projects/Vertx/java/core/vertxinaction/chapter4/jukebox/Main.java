package core.vertxinaction.chapter4.jukebox;

import io.vertx.core.Vertx;

public class Main {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle("chapter4.jukebox.Jukebox");
        vertx.deployVerticle("chapter4.jukebox.NetControl");
    }
}