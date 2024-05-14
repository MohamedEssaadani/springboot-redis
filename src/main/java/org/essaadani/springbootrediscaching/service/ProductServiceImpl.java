package org.essaadani.springbootrediscaching.service;

import org.essaadani.springbootrediscaching.entities.ProductEntity;
import org.essaadani.springbootrediscaching.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductEntity create(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    @Cacheable(value = "products", key = "#id}")
    public ProductEntity readOne(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    @Cacheable(value = "products")
    public List<ProductEntity> readAll() {
        return productRepository.findAll();
    }

    @Override
    @CachePut(value = "products", key = "#product.id")
    public ProductEntity update(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    @CacheEvict(value = "products", key = "#id", beforeInvocation = true)
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
