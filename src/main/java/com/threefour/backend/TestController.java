package com.threefour.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String sayHello(){
        return "Hello World";
    }

    @GetMapping("api/test")
    public String testTwo(){
        return "This is an test";
    }


}
