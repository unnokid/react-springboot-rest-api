package com.example.gccoffee.service;

import com.example.gccoffee.model.Category;
import com.example.gccoffee.model.Product;
import com.example.gccoffee.repository.ProductJdbcRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class DefaultProductService implements ProductService {

    private final ProductJdbcRepository productJdbcRepository;

    public DefaultProductService(ProductJdbcRepository productJdbcRepository) {
        this.productJdbcRepository = productJdbcRepository;
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productJdbcRepository.findByCategory(category);
    }

    @Override
    public List<Product> getAllProducts() {
        return productJdbcRepository.findAll();
    }

    @Override
    public Product createProduct(String productName, Category category, long price) {
        Product product = new Product(UUID.randomUUID(), productName, category, price);
        return productJdbcRepository.insert(product);
    }

    @Override
    public Product createProduct(String productName, Category category, long price, String description) {
        Product product = new Product(UUID.randomUUID(), productName, category, price, description, LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS),LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        return productJdbcRepository.insert(product);
    }
}
