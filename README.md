
#multithreading 
#spring-boot 

## Notes 
- @async annotation tells that method annotated with it would run in a different thread. 
- So that main thread becomes free to execute different process. 
- This for sure does not mean that we give response faster to the caller/ client application.
- Just by adding @async annotation on the method, does not make it async. For that to work we need to make sure that we have enabled async functionality on the spring boot application as well by using @enableAsync annotation. 
- If we don't need to care about the response from the long running process, then we don't need to return completableFuture. In that case our method would return out without waiting for the completion of the long running process. 

## Important points to remember
- @async annotation can only work on public methods. Not on private methods. 
	- because it works using proxies methods. aspects. 
	- in case of private method, it would bypass the proxied method. 
- It has to return either void or CompletableFuture. Nothing else is allowed. 
- Can override underlying executor service. Two ways 
	- method level 

```JAVA

@Configuration  
@EnableAsync  
public class SpringAsyncConfig {  
  
    @Bean(name = "threadPoolTaskExecutor")  
    public Executor threadPoolTaskExecutor() {  
        return new ThreadPoolTaskExecutor();  
    }  
}

@Async("threadPoolTaskExecutor")

public void asyncMethodWithConfiguredExecutor() {

    System.out.println("Execute method with configured executor - "

      + Thread.currentThread().getName());

}
```

- global scope 


```java
@Configuration
@EnableAsync
public class SpringAsyncConfig implements AsyncConfigurer {

@Override 
public Executor getAsyncExecutor() {

ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
threadPoolTaskExecutor.initialize();
return threadPoolTaskExecutor; 
											 
}


```


### Exception handling 
- If return type is future, future.get would throw the exception. Which is simple. 
	- we can use normal completableFuture exceptionally method. 
- But for Void type return we need to do something extra .
	- need to create a class implementing the interface named `AsyncExceptionHandler` and overriding a method named `handleUncaughtException`

```JAVA
@Configuration  
  
public class AsyncConfig implements AsyncConfigurer {  
  
    @Override  
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {  
        System.out.println("custom asyncException handler is registered");  
        return new AsyncExceptionHandler();  
  
    }  
}
```

### Things to try out from the program:
- Do we really need to return a completableFuture from a @async method? => YES 
	- What happens if we don't return completableFuture and instead return normal data? => Compile time error 
	- Does main thread wait before returning the response? => don't think about it, this is not allowed only. 
- 
	
