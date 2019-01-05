package com.qurich.servicesconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value="services-provider",fallback = HelloWorldHystric.class)
public interface IHelloWorld {

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String hi();

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(@RequestParam(value="name", required=false, defaultValue="guest")String name);

}
