package verticles.deploy;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Launcher;
import io.vertx.core.ThreadingModel;
import io.vertx.core.json.JsonObject;

public class DeployExample extends AbstractVerticle
{

    public static void main(String[] args)
    {
        Launcher.executeCommand("run", DeployExample.class.getName());
    }

    @Override
    public void start() throws Exception
    {

        System.out.println("Main verticle has started, let's deploy some others...");

        // Different ways of deploying verticles

        // Deploy a verticle and don't wait for it to start
        vertx.deployVerticle("io.vertx.example.core.verticle.deploy.OtherVerticle",new DeploymentOptions().setConfig(new JsonObject().put("config","normal deploy")));

        // Deploy another instance and  want for it to start
        vertx.deployVerticle("io.vertx.example.core.verticle.deploy.OtherVerticle",new DeploymentOptions().setConfig(new JsonObject().put("config","async deploy")), res -> {
            if(res.succeeded())
            {

                String deploymentID = res.result();

                System.out.println("Other verticle deployed ok, deploymentID = " + deploymentID);

                // You can also explicitly undeploy a verticle deployment.
                // Note that this is usually unnecessary as any verticles deployed by a verticle will be automatically
                // undeployed when the parent verticle is undeployed

                vertx.undeploy(deploymentID, res2 -> {
                    if(res2.succeeded())
                    {
                        System.out.println("Undeployed ok!");
                    }
                    else
                    {
                        res2.cause().printStackTrace();
                    }
                });

            }
            else
            {
                res.cause().printStackTrace();
            }
        });

        // Deploy specifying some config
        JsonObject config1 = new JsonObject().put("confif", "some-config");

        vertx.deployVerticle("io.vertx.example.core.verticle.deploy.OtherVerticle", new DeploymentOptions().setConfig(config1));

        // Deploy 10 instances
        JsonObject config2 = new JsonObject().put("confif", "10-instances");

        vertx.deployVerticle("io.vertx.example.core.verticle.deploy.OtherVerticle", new DeploymentOptions().setInstances(10).setConfig(config2));

        // Deploy it as a worker verticle
        JsonObject config3 = new JsonObject().put("confif", "worker-verticle");
        vertx.deployVerticle("io.vertx.example.core.verticle.deploy.OtherVerticle", new DeploymentOptions().setThreadingModel(ThreadingModel.WORKER).setConfig(config3));


    }
}