package com.microservice.imghandler.worker;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BackgroundWorker {
    private final Executor executor = Executors.newFixedThreadPool(10);

    public void submitTask(Runnable task) {
        executor.execute(task);
    }
}
