package chapter3eventbus.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

import java.util.Random;
import java.util.UUID;

public class Sender extends AbstractVerticle
{

    @Override
    public void start()
    {
        EventBus eventBus = vertx.eventBus();
        var randomUUID = UUID.randomUUID();

        vertx.setPeriodic(1000, id -> {
            var rand = new Random();
            String data = rand.nextInt(20, 40) + " " + randomUUID;
            eventBus.publish("ping-channel", data);
            System.out.println("Sent: " + data);
        });
    }
}