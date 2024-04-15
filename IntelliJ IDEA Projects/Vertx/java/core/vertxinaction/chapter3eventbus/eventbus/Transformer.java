package core.vertxinaction.chapter3eventbus.eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class Transformer extends AbstractVerticle
{

    @Override
    public void start()
    {
        EventBus eventBus = vertx.eventBus();

        eventBus.consumer("ping-channel", message -> {

            var data = Integer.parseInt(message.body().toString().split(" ")[0]);

            if(data % 2 == 0)
            {
                eventBus.publish("pong-channel", "Even number generated!!");
            }
            else
            {
                eventBus.publish("pong-channel", "Odd number generated!!");
            }


        });
    }
}