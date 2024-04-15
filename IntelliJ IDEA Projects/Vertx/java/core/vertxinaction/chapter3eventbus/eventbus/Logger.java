package core.vertxinaction.chapter3eventbus.eventbus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;

public class Logger extends AbstractVerticle {

    @Override
    public void start() {
        EventBus eventBus = vertx.eventBus();

        // Register a consumer for the "pong-channel"
        eventBus.consumer("pong-channel", message -> {
            System.out.println("Logged: " + message.body());
        });
    }
}