package com.example.SpringAsync;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api")
public class SampleController {

    private final SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @ExceptionHandler(ArithmeticException.class)
    public SampleErrorResponseModel handleException(Exception exception) {

        SampleErrorResponseModel model = new SampleErrorResponseModel();
        model.setMessage(ArithmeticException.class.getName() + " => " + exception.getMessage());
        model.setTimestamp(LocalDateTime.now());
        model.setCode(exception.getClass().getName());

        return model;

    }

    @GetMapping("/name")
    CompletableFuture<String> getName() throws UnknownHostException, InterruptedException {

        // Local address
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        String hostName = InetAddress.getLocalHost().getHostName();
        CompletableFuture<String> stringCompletableFuture = this.sampleService.longRunningProcess();

        //log - indicating that we are back in main thread after the long-running process initiation
        System.out.println("log - indicating that we are back in main thread after the long running process initiation");

        String serverDetails = "Current address : " + hostAddress + " : " + hostName;

        return stringCompletableFuture
                .thenApply(s -> s + "---" + serverDetails);

    }

    @GetMapping("/void")
    String getVoid() throws UnknownHostException, InterruptedException {

        // Local address
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        String hostName = InetAddress.getLocalHost().getHostName();

        this.sampleService.longRunningProcessNotReturningAnything();

        //log - indicating that we are back in main thread after the long-running process initiation
        System.out.println("log - indicating that we are back in main thread after the long running process initiation");

        String serverDetails = "Current address : " + hostAddress + " : " + hostName;

        return serverDetails + " request taken...";

    }

}