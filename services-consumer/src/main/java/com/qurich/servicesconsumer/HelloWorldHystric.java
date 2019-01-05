package com.qurich.servicesconsumer;

import org.springframework.stereotype.Component;

/**
 * 熔断器
 */
@Component
public class HelloWorldHystric implements  IHelloWorld {


    @Override
    public String hi() {
        return "sorry,network is error";
    }

    @Override
    public String hello(String name) {
        return "sorry "+name+",network is error";
    }
}
