package com.threefour.backend.Janindu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JaninduController {

    @GetMapping("/jdh")
    public String hello(){
        return "Hello Janindu!!!";

    }

    @GetMapping("/getsum")
    public String ht(){
        int num1 = 10 ;
        int num2 = 20 ;
        return JaninduService.addnumbers(num1 , num2);
    }

}
