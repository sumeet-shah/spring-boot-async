package com.example.SpringAsync;

import lombok.SneakyThrows;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;
import java.util.Arrays;

public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @SneakyThrows
    @Override
    public void handleUncaughtException(Throwable ex, Method method, @SuppressWarnings("NullableProblems") Object... params) {
        System.out.println("exception handler start----------");
        System.out.println(ex.getMessage());
        System.out.println(method.getName());
        System.out.println(Arrays.toString(params));
        System.out.println("exception handler end----------");

        throw ex;

    }
}