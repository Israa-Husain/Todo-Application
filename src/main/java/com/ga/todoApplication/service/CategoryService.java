package com.ga.todoApplication.service;

import com.ga.food.exception.InformationExistException;
import com.ga.food.exception.InformationNotFoundException;
import com.ga.todoApplication.model.Category;
import com.ga.todoApplication.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @PutMapping("/categories")
    public Category createCategory(Category categoryObject){
        System.out.println("Service calling createCategory ==> ");
        Category category = categoryRepository.findByName(categoryObject.getName());
        if(category!=null){
            throw new InformationExistException("category with name "+category.getName() + " already exist.");
        } else {
            return categoryRepository.save(categoryObject);
        }
    }


    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Long id){
        return categoryRepository.findById(id).orElseThrow(()->new InformationNotFoundException("category with id "+id+" not found"));
    }
}


