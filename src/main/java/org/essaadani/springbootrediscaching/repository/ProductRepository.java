package org.essaadani.springbootrediscaching.repository;

import org.essaadani.springbootrediscaching.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
