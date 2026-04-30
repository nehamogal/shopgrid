package com.shopgrid.service;

import com.shopgrid.dto.ProductRequest;
import com.shopgrid.dto.ProductResponse;
import com.shopgrid.exception.ProductNotFoundException;
import com.shopgrid.model.Product;
import com.shopgrid.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
                .map(this::mapToResponse);
    }
    
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return mapToResponse(product);
    }
    
    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product(request.getName(), request.getPrice(), request.getDescription());
        Product saved = productRepository.save(product);
        return mapToResponse(saved);
    }
    
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        
        Product updated = productRepository.save(product);
        return mapToResponse(updated);
    }
    
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        productRepository.deleteById(id);
    }
    
    private ProductResponse mapToResponse(Product product) {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getDescription()
        );
    }
}