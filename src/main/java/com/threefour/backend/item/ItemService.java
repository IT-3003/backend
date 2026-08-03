package com.threefour.backend.item;


import org.springframework.stereotype.Service;


import java.util.List;




import com.threefour.backend.promotion.PromotionRepository;
import com.threefour.backend.order.OrderItemRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {


    private final ItemRepository itemRepository;
    private final PromotionRepository promotionRepository;
    private final OrderItemRepository orderItemRepository;


    public ItemService(ItemRepository itemRepository, PromotionRepository promotionRepository,
                       OrderItemRepository orderItemRepository) {
        this.itemRepository = itemRepository;
        this.promotionRepository = promotionRepository;
        this.orderItemRepository = orderItemRepository;
    }


    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }
    public Item getItemById(Long id) {
        // Fetches all columns for the specific primary key
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }


    @Transactional
    public void deleteItem(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

        promotionRepository.deleteByItem_ItemId(id);
        orderItemRepository.deleteByProduct_ItemId(id);
        itemRepository.delete(item);
    }


    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }


    public Item updateItem(Long id, Item updatedItem) {


        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));


        item.setItemName(updatedItem.getItemName());
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