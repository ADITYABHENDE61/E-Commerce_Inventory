package com.ecommerce.inventory.service;

import com.ecommerce.inventory.entity.Category;
import com.ecommerce.inventory.entity.Product;
import com.ecommerce.inventory.repository.CategoryRepository;
import com.ecommerce.inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    // Add Product
    public Product addProduct(Product product, Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category Not Found"));

        product.setCategory(category);

        return productRepository.save(product);
    }

    // Get Products with Pagination & Sorting
    public Page<Product> getProducts(int page, int size, String sortBy, String direction) {

        Sort sort = direction.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return productRepository.findAll(pageable);
    }

    // Get Product by Id
    public Product getProduct(Long id) {

        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
    }

    // Update Product
    public Product updateProduct(Long id, Product product) {

        Product oldProduct = getProduct(id);

        oldProduct.setName(product.getName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setQuantity(product.getQuantity());

        return productRepository.save(oldProduct);
    }

    // Delete Product
    public void deleteProduct(Long id) {

        productRepository.deleteById(id);
    }

}