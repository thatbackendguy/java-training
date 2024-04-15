package core.vertxinaction.chapter4;

import io.vertx.core.Vertx;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;

public class ReadingFilesVertxStreams
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

        OpenOptions opts = new OpenOptions().setRead(true);

        vertx.fileSystem().open("/home/yash/ftp-server.txt", opts, ar -> {
            if(ar.succeeded())
            {
                AsyncFile file = ar.result();

                file.handler(System.out::println)
                        .exceptionHandler(Throwable::printStackTrace)
                        .endHandler(done -> {
                    System.out.println("\n-----| DONE |-----");
                    vertx.close();
                });
            }
            else
            {
                ar.cause().printStackTrace();
            }
        });
    }
}
