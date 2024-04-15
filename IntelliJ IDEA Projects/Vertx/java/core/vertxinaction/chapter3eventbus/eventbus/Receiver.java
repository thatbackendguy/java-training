package core.vertxinaction.chapter3eventbus.eventbus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class Receiver extends AbstractVerticle {

    @Override
    public void start() {
        EventBus eventBus = vertx.eventBus();

        // Register a consumer for the "ping-channel"
        eventBus.consumer("ping-channel", message -> {
            System.out.println("Received: " + message.body());
        });
    }
}