package com.luu.dubbo;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

public interface DemoService {

    public String sayHello(String name);

    public CompletableFuture<String> sayHelloAsync(String name);
}
