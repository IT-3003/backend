package com.threefour.backend.dinuvi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class dinuviController {
    @GetMapping("/dinuvi")
    public String hello(){
        return "hello dinuvi!";
    }

    @GetMapping("/getsum")
    public String ht(){
        int num1 = 34;
        int num2 = 20;

        return dinuviService.addnumbers(num1, num2);

    }
}


