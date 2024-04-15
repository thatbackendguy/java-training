package vertxdocs.filesystem;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;

public class Main
{
    public static void main(String[] args)
    {
        Vertx vertx = Vertx.vertx();

//        vertx.fileSystem()
//                .readFile("sample.txt")
//                .onComplete(result -> {
//                    if (result.succeeded()) {
//                        System.out.println(result.result());
//                    } else {
//                        System.err.println("Oh oh ..." + result.cause());
//                    }
//                });

        // Copy a file
//        vertx.fileSystem()
//                .copy("sample.txt", "copy.txt")
//                .onComplete(result -> {
//                    if (result.succeeded()) {
//                        System.out.println("File copied");
//                    } else {
//                        System.err.println("Oh oh ..." + result.cause());
//                    }
//                });

        // Write a file
//        vertx.fileSystem()
//                .writeFile("sample-write.txt", Buffer.buffer("Sample file written from buffer"))
//                .onComplete(result -> {
//                    if (result.succeeded()) {
//                        System.out.println("File written");
//                    } else {
//                        System.err.println("Oh oh ..." + result.cause());
//                    }
//                });

        // Check existence and delete
//        vertx.fileSystem()
//                .exists("copy.txt")
//                .compose(exist -> {
//                    if (exist) {
//                        return vertx.fileSystem().delete("copy.txt");
//                    } else {
//                        return Future.failedFuture("File does not exist");
//                    }
//                }).onComplete(result -> {
//                    if (result.succeeded()) {
//                        System.out.println("File deleted");
//                    } else {
//                        System.err.println("Oh oh ... - cannot delete the file: " + result.cause().getMessage());
//                    }
//                });
        // Copy file from foo.txt to bar.txt synchronously
//        fs.copyBlocking("sample.txt", "copyBlocking.txt");
}
}
