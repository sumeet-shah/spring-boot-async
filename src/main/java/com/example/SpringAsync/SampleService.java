package com.example.SpringAsync;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class SampleService {

    @Async
    public CompletableFuture<String> longRunningProcess() throws InterruptedException {

        System.out.println("Long running process start");
        Thread.sleep(10000);
        System.out.println("Long running process end");

        int o = 100/0;

        return CompletableFuture.completedFuture("return from long running process");
    }

    @Async
    public  void longRunningProcessNotReturningAnything() throws InterruptedException {
        System.out.println("Long running process start - void");
        Thread.sleep(10000);
        System.out.println("Long running process end - void ");
        int o = 100/0;
    }

}