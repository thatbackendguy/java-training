package core.vertxinaction.chapter2verticles;

import io.vertx.core.Context;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ExecuteBlockingDemo extends AbstractVerticle
{
    private static Logger LOGGER = LoggerFactory.getLogger(ExecuteBlockingDemo.class);

    public static void main(String[] args)
    {
        var map = new ConcurrentHashMap<>();

        map.put("ip", "10.20.40.227");
        map.put("port", "22");

        Vertx vertx = Vertx.vertx();

        Context ctx = vertx.getOrCreateContext();

        ctx.put(1, map);

        ctx.runOnContext(v -> {
            LOGGER.info("Context data: {} {}", (Map) ctx.get(1), Thread.currentThread().getName());
        });

        vertx.deployVerticle(new ExecuteBlockingDemo());
    }

    @Override
    public void start()
    {
        vertx.setPeriodic(2000, id -> {
            LOGGER.info(String.valueOf(Instant.now().getEpochSecond()));

            vertx.executeBlocking(this::blockingCode, this::resultHandler);
        });
    }

    public void blockingCode(Promise<String> promise)
    {
        try
        {
            Thread.sleep(5000);

            promise.complete("Success @ " + Thread.currentThread().getName());

        } catch(InterruptedException e)
        {
            promise.fail(e);
        }
    }

    public void resultHandler(AsyncResult<String> result)
    {
        if(result.succeeded())
        {
            LOGGER.info("result : {}", result.result());
        }
        else
        {
            LOGGER.error("result : {}", result.cause());
        }
    }
}
