package com.ga.todoApplication.service;

import com.ga.todoApplication.exception.InformationNotFoundException;
import com.ga.todoApplication.model.Category;
import com.ga.todoApplication.model.Item;
import com.ga.todoApplication.repository.CategoryRepository;
import com.ga.todoApplication.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Item> getItems(Long categoryId){
        System.out.println("service calling getItems ==>");
        categoryRepository.findById(categoryId).orElseThrow(()-> new InformationNotFoundException("category with id "+categoryId+" not found"));
        return itemRepository.findByCategoryId(categoryId);
    }

    public Item createItem(Long categoryId, Item item){
        System.out.println("service calling createItem ==>");
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new InformationNotFoundException("category with id "+categoryId+" not found"));
        item.setCategory(category);
        return itemRepository.save(item);
    }

    public Item getItem(Long categoryId, Long itemId){
        System.out.println("service calling getItem ==>");
        Item item = itemRepository.findById(categoryId).orElseThrow(()-> new InformationNotFoundException("category with id "+categoryId+" not found"));

        if(!item.getCategory().getId().equals(categoryId)){
            throw new InformationNotFoundException("item not found in this category");
        }
        return item;
    }

    public Item updateItem(Long categoryId, Long itemId, Item itemObject){
        System.out.println("service calling updateItem ==>");
        Item item = getItem(categoryId,itemId);
        item.setName(itemObject.getName());
        item.setDescription(itemObject.getDescription());
        item.setDueDate(itemObject.getDueDate());
        return itemRepository.save(item);
    }

    public Item deleteItem(Long categoryId, Long itemId){
        System.out.println("service calling deleteItem ==>");
        Item item = getItem(categoryId,itemId);
        itemRepository.deleteById(itemId);
        return item;
    }



}
