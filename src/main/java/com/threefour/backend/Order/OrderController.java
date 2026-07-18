package com.threefour.backend.Order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @GetMapping("/rav")
    public String hello(){
        return "Hello Ruchihta";
    }

    @GetMapping("/getsum")
    public String nw(){
        int a = 1;
        int b = 2;

        return OrderService.addnumbers(a,b);

    }


}
