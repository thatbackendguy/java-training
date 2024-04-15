package core.vertxdocs.jsonparser;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

public class POJOSenderVerticle extends AbstractVerticle
{
    @Override
    public void start()
    {
        User user = new User("yash", "prajapati", 22, "yash@gmail.com");

        JsonObject json = JsonObject.mapFrom(user);

        vertx.setPeriodic(1000, res -> {
            vertx.eventBus().publish("user.pojo", json);

            System.out.println("Data sent!");
        });
    }
}
