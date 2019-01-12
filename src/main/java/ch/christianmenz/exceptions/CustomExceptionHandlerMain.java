package ch.christianmenz.exceptions;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class CustomExceptionHandlerMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ForkJoinPool forkJoinPool = new ForkJoinPool(8,ForkJoinPool.defaultForkJoinWorkerThreadFactory , new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Gotcha");
                e.printStackTrace();
            }
        }, false);

        forkJoinPool.execute(() -> {
            RecursiveTask<String> task$ = new RecursiveTask<String>() {
                @Override
                protected String compute() {
                    System.out.println("Going to throw up!");
                    throw new NullPointerException();
                }
            };

            task$.fork();
            task$.join(); // throws Exception
            System.out.println("done");

        });

        forkJoinPool.awaitTermination(1, TimeUnit.SECONDS);
    }
}
