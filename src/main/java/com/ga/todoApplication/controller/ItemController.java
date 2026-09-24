package com.ga.todoApplication.controller;

import com.ga.todoApplication.model.Item;
import com.ga.todoApplication.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/categories/{id}/items")
    public List<Item> getItems(@PathVariable Long id){
        return itemService.getItems(id);
    }

    @GetMapping("/categories/{categoryId}/items/{itemId}")
    public Item getItem(@PathVariable Long categoryId, @PathVariable Long itemId) {
        return itemService.getItem(categoryId, itemId);
    }

    @PostMapping("/categories/{id}/items")
    public Item createItem(@PathVariable Long id, @RequestBody Item itemObject){
        return itemService.createItem(id,itemObject);
    }

    @PutMapping("/categories/{categoryId}/items/{itemId}")
    public Item updateItem(@PathVariable Long categoryId, @PathVariable Long itemId, @RequestBody Item itemObject){
        return itemService.updateItem(categoryId, itemId, itemObject);
    }

    @DeleteMapping("/categories/{categoryId}/items/{itemId}")
    public Item deleteItem(@PathVariable Long categoryId, @PathVariable Long itemId){
        return itemService.deleteItem(categoryId, itemId);
    }




}
