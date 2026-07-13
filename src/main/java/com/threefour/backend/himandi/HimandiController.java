package com.threefour.backend.himandi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HimandiController {
    @GetMapping("/hasi")
    public String hello() {
        return "Hello Himandiiiiiii";
    }
    @GetMapping("/getsum")
    public String ht() {
        int num1 = 10;
        int num2 = 20;

        return HimandiService.addnumbers(num1, num2);
    }

}