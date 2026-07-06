package com.ecommerce.inventory.controller;

import com.ecommerce.inventory.entity.Product;
import com.ecommerce.inventory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
@Valid
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Add Product
    @PostMapping("/{categoryId}")
    public Product addProduct(@Valid @RequestBody Product product,
                              @PathVariable Long categoryId) {

        return productService.addProduct(product, categoryId);
    }

    // Get Products
    @GetMapping
    public Page<Product> getProducts(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction

    ) {

        return productService.getProducts(page, size, sortBy, direction);

    }

    // Get Product By Id
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {

        return productService.getProduct(id);

    }

    // Update Product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,
                                 @RequestBody Product product) {

        return productService.updateProduct(id, product);

    }

    // Delete Product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        return "Product Deleted Successfully";
    }

}