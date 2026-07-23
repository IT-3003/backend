package com.threefour.backend.item;

import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private final ItemRepository dinuviRepository;

    public ItemService(ItemRepository dinuviRepository) {
        this.dinuviRepository = dinuviRepository;
    }

    public String addNumbers(int a, int b) {
        return "The sum is " + (a + b);
    }

    public Item saveDinuvi(Item dinuvi) {
        return dinuviRepository.save(dinuvi);
    }
}