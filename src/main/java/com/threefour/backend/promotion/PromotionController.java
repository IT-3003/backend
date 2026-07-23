package com.threefour.backend.promotion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/promotion")
public class PromotionController {
    private final PromotionService anshuService;


    public PromotionController(PromotionService anshuService) {
        this.anshuService = anshuService;
    }

    @GetMapping("/asa")
    public String hello(){
        return"Hello Anshu";
}
@PostMapping("/create")
public ResponseEntity<Promotion> create(@RequestBody Promotion anshu) {
    Promotion savedAnshu = anshuService.saveAnshu(anshu);
    return new ResponseEntity<>(savedAnshu, HttpStatus.CREATED);
}



}
