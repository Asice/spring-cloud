package com.qurich.servicesprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${mysql.username}")
    private String mysqlName;

    @Value("${server.port}")
    private String port;

    @GetMapping("/hi")
    public String hi() {
        return "Hi,I am port:"+port+",And I fetch mysql config["+mysqlName+"] from https://github.com/Asice/configTempale/blob/master/springcloud-bus-demo.properties";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value="name", required=false, defaultValue="guest")String name) {
        return "hello "+name+",I am port:"+port;
    }
}
