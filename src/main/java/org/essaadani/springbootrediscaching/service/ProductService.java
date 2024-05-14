package org.essaadani.springbootrediscaching.service;

import org.essaadani.springbootrediscaching.entities.ProductEntity;

import java.util.List;

public interface ProductService {
    ProductEntity create(ProductEntity product);

    ProductEntity readOne(Long id);

    List<ProductEntity> readAll();

    ProductEntity update(ProductEntity product);

    void deleteById(Long id);
}
