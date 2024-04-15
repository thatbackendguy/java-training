package core.vertxdocs.jsonparser;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

public class POJORecvVerticle extends AbstractVerticle
{
    @Override
    public void start()
    {
        vertx.eventBus().consumer("user.pojo", msg -> {

            JsonObject json = (JsonObject) msg.body();

            System.out.println("Received JSON: " + json);

            User user = json.mapTo(User.class);

            System.out.println(user.getFirstName());

        });
    }
}
