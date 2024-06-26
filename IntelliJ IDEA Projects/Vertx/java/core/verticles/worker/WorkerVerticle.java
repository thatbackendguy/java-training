package verticles.worker;

import io.vertx.core.AbstractVerticle;

public class WorkerVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        System.out.println("[Worker] Starting in " + Thread.currentThread().getName());

        vertx.eventBus().<String>consumer("sample.data", message -> {
            System.out.println("[Worker] Consuming data in " + Thread.currentThread().getName());

            String body = "I am "+config().getString("ip");

            message.reply(body);
        });
    }
}