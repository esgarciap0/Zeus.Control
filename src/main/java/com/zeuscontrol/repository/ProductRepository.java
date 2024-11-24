package com.zeuscontrol.repository;

import com.zeuscontrol.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(String id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public void save(Product product) {
        products.add(product);
    }

    public void update(Product product) {
        findById(product.getId()).ifPresent(existing -> {
            existing.setName(product.getName());
            existing.setCategory(product.getCategory());
            existing.setPrice(product.getPrice());
            existing.setQuantity(product.getQuantity());
        });
    }

    public void delete(String id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
