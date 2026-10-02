package com.financial.comments.domain.port;

import com.financial.comments.domain.model.Product;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    
    Product save(Product product);
    
    Optional<Product> findById(UUID id);
    
    boolean existsById(UUID id);
    
    boolean existsByName(String name);
    
    void deleteById(UUID id);
}