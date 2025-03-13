package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // CREATE a new product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // GET all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // GET a product by ID
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    // UPDATE a product by ID
    public Product updateProduct(String id, Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            return productRepository.save(product);
        }).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // DELETE a product by ID
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}