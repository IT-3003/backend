package com.threefour.backend.himandi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HimandiController {
    @GetMapping("/hasi")
    public String hello() {
        return "Hello Himandiiiiiii";
    }


}