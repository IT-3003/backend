package com.threefour.backend.item;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService dinuviService) {
        this.itemService = dinuviService;
    }

    @GetMapping
    public String hello() {
        return "Dinuvi!";
    }

    @PostMapping("/create")
    public ResponseEntity<Item> create(@RequestBody Item item) {
        Item savedDinuvi = itemService.saveItem(item);
        return new ResponseEntity<>(savedDinuvi, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id,
                                           @RequestBody Item item) {

        Item updatedItem = itemService.updateItem(id, item);
        return ResponseEntity.ok(updatedItem);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok("Item deleted successfully.");
    }
}