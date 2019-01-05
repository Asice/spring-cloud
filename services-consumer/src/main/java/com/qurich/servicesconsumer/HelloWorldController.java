package com.qurich.servicesconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通过Feign 在IHelloWorld接口中声明@FeignClient(value="services-provider",fallback = HelloWorldHystric.class)
 * 通过http调用services-provider 服务中的方法
 */
@RestController
public class HelloWorldController {

    @Autowired
    private IHelloWorld iHelloWorld;

    @GetMapping("/hi")
    public String hi(){
        return iHelloWorld.hi();
    }

    @GetMapping("/hello")
    public String hello(){
        return iHelloWorld.hello("jack");
    }
}
