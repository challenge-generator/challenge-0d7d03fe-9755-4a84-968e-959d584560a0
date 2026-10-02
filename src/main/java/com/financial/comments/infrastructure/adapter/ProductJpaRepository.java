package com.financial.comments.infrastructure.adapter;

import com.financial.comments.domain.model.Product;
import com.financial.comments.domain.port.ProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductJpaRepository extends JpaRepository<Product, UUID>, ProductRepository {

    @Query("SELECT p FROM Product p WHERE p.status = 'ACTIVE' AND p.id = :id")
    Optional<Product> findActiveById(@Param("id") UUID id);

    @Query("SELECT p FROM Product p WHERE p.status = 'ACTIVE'")
    List<Product> findAllActive();

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Product p WHERE p.id = :id AND p.status = 'ACTIVE'")
    boolean existsActiveById(@Param("id") UUID id);

    @Override
    default Product save(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        return saveProduct(product);
    }

    private Product saveProduct(Product product) {
        return save(product);
    }

    @Override
    default Optional<Product> findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del producto no puede ser nulo");
        }
        return JpaRepository.super.findById(id);
    }

    @Override
    default boolean existsById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del producto no puede ser nulo");
        }
        return existsById(id);
    }

    @Override
    default boolean isActive(UUID id) {
        if (id == null) {
            return false;
        }
        return existsActiveById(id);
    }

    @Override
    default Optional<Product> findActiveProductById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del producto no puede ser nulo");
        }
        return findActiveById(id);
    }
}