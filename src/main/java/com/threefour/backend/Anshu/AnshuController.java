package com.threefour.backend.Anshu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class AnshuController {
    @GetMapping("/asa")
    public String hello(){
        return"Hello Anshu";
}




}
