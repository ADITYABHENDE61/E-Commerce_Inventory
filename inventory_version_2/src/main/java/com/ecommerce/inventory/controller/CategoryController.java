package com.ecommerce.inventory.controller;

import com.ecommerce.inventory.entity.Category;
import com.ecommerce.inventory.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Valid
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @NotBlank(message="Category name is required")
    private String name;
    @PostMapping
    public Category addCategory(@Valid @RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id,
                                   @RequestBody Category category){

        return categoryService.updateCategory(id,category);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id){

        categoryService.deleteCategory(id);

        return "Category Deleted Successfully";
    }
}