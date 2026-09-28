package com.ga.todoApplication.repository;

import com.ga.todoApplication.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName); //method name matters, the attributes name does not
    Category findByNameAndDescription(String categoryName, String categoryDescription);
    Category findByUserIdAndName(Long userId, String categoryName);
    List<Category> findByUserId(Long userId);
}
