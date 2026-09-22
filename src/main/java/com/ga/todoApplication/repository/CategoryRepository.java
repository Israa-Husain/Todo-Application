package com.ga.todoApplication.repository;

import com.ga.todoApplication.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String categoryName); //method name matters, the attributes name does not
}
