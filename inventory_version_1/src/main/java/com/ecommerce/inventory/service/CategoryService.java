package com.ecommerce.inventory.service;

import com.ecommerce.inventory.entity.Category;
import com.ecommerce.inventory.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found"));
    }

    public Category updateCategory(Long id, Category category){

        Category oldCategory = getCategoryById(id);

        oldCategory.setName(category.getName());

        return categoryRepository.save(oldCategory);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}