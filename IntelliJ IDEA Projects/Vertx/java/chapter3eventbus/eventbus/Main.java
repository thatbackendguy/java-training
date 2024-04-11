package chapter3eventbus.eventbus;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class Main
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle("chapter3eventbus.eventbus.Sender", new DeploymentOptions().setInstances(3));
        vertx.deployVerticle("chapter3eventbus.eventbus.Receiver");
        vertx.deployVerticle("chapter3eventbus.eventbus.Logger");
        vertx.deployVerticle("chapter3eventbus.eventbus.Transformer");
    }
}
