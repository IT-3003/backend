package com.threefour.backend.ruchitha;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RuchithaController {

    @GetMapping("/rav")
    public String hello(){
        return "Hello Ruchihta";
    }

    @GetMapping("/getsum")
    public String nw(){
        int a = 1;
        int b = 2;

        return RuchithaService.addnumbers(a,b);

    }


}
