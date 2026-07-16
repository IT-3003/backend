package com.threefour.backend.dinuvi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class dinuviController {
    @GetMapping("/dinuvi")
    public String hello(){
        return "hello dinuvi!";
    }


}


