package com.threefour.backend.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/rav")
    public String hello(){
        return "Hello Ruchihta";
    }




}
