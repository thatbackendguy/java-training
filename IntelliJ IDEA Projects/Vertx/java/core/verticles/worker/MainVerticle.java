package verticles.worker;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Launcher;
import io.vertx.core.ThreadingModel;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An example illustrating how worker verticles can be deployed and how to interact with them.
 * <p>
 * This example prints the name of the current thread at various locations to exhibit the event loop <-> worker
 * thread switches.
 */

public class MainVerticle extends AbstractVerticle
{
    private static Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class);

    public static void main(String[] args)
    {
        Launcher.executeCommand("run", MainVerticle.class.getName());
    }

    @Override
    public void start() throws Exception
    {
        LOGGER.info("[Main] Running in {}", Thread.currentThread().getName());

        vertx.deployVerticle("io.vertx.example.core.verticle.worker.WorkerVerticle", new DeploymentOptions().setThreadingModel(ThreadingModel.WORKER).setConfig(new JsonObject().put("ip","10.20.40.227")));

        vertx.eventBus().request("sample.data", "hello vert.x", r -> {
            System.out.println("[Main] Receiving reply '" + r.result().body() + "' in " + Thread.currentThread().getName());
        });
    }
}