package com.threefour.backend.item;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dinuvi")
public class ItemController {

    private final ItemService dinuviService;

    public ItemController(ItemService dinuviService) {
        this.dinuviService = dinuviService;
    }

    @GetMapping
    public String hello() {
        return "Dinuvi!";
    }

    @PostMapping("/create")
    public ResponseEntity<Item> create(@RequestBody Item dinuvi) {
        Item savedDinuvi = dinuviService.saveDinuvi(dinuvi);
        return new ResponseEntity<>(savedDinuvi, HttpStatus.CREATED);
    }
}