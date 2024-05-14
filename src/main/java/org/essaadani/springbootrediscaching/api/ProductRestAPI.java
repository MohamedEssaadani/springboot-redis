package org.essaadani.springbootrediscaching.api;

import org.essaadani.springbootrediscaching.entities.ProductEntity;
import org.essaadani.springbootrediscaching.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestAPI {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductEntity> readAll() {
        return productService.readAll();
    }

    @GetMapping("/{id}")
    public ProductEntity readOne(@PathVariable Long id) {
        return productService.readOne(id);
    }

    @PutMapping
    public ProductEntity update(@RequestBody ProductEntity product) {
        return productService.update(product);
    }
}
