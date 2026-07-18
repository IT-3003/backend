package com.threefour.backend.dinuvi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dinuvi")
public class DinuviController {

    private final DinuviService dinuviService;

    public DinuviController(DinuviService dinuviService) {
        this.dinuviService = dinuviService;
    }

    @GetMapping
    public String hello() {
        return "Dinuvi!";
    }

    @PostMapping("/create")
    public ResponseEntity<Dinuvi> create(@RequestBody Dinuvi dinuvi) {
        Dinuvi savedDinuvi = dinuviService.saveDinuvi(dinuvi);
        return new ResponseEntity<>(savedDinuvi, HttpStatus.CREATED);
    }
}