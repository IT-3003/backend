package com.threefour.backend.thinula;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThinulaController {
    @GetMapping("/getname")
    public String hello(){
        return "Thinula";
    }

    @GetMapping("/getsum")
    public String ht(){
        int num1 = 10;
        int num2 = 20;

        return ThinulaService.addnumbers(num1, num2);
    }

}
