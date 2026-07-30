package com.threefour.backend.item;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }
    public Item getItemById(Long id) {
        // Fetches all columns for the specific primary key
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

        itemRepository.delete(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item updateItem(Long id, Item updatedItem) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

        item.setitemName(updatedItem.getitemName());
        item.setCategory(updatedItem.getCategory());
        item.setBaseprice(updatedItem.getBaseprice());
        item.setBrand(updatedItem.getBrand());
        item.setImageUrl(updatedItem.getImageUrl());
        item.setStockQuantity(updatedItem.getStockQuantity());
        item.setCostPrice(updatedItem.getCostPrice());
        item.setDescription(updatedItem.getDescription());

        return itemRepository.save(item);
    }

}