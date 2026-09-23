package com.ga.todoApplication.controller;

import com.ga.todoApplication.model.Category;
import com.ga.todoApplication.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path="/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category categoryObject) {
        System.out.println("Calling createCategory ==> ");
        return categoryService.createCategory(categoryObject);
    }

    @GetMapping("/categories")
    public List<Category> getCategory(){
        System.out.println("calling getCategory ==>");
        return categoryService.getCategories();
    }

    @GetMapping("/categories/{id}")
    public Category getCategory(@PathVariable Long id){
        System.out.println("calling getCategory ==>");
        return categoryService.getCategory(id);
    }

    @PutMapping("/categories/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category object){
        return categoryService.updateCategory(id,object);
    }

    @DeleteMapping("/categories/{id}")
    public Category deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }
}
