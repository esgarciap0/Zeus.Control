package com.zeuscontrol.service;

import com.zeuscontrol.model.Product;
import com.zeuscontrol.repository.ProductRepository;

import java.util.List;

public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> listAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(String id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new Exception("Product not found"));
    }

    public void addProduct(Product product) {
        repository.save(product);
    }

    public void updateProduct(Product product) throws Exception {
        if (repository.findById(product.getId()).isEmpty()) {
            throw new Exception("Product not found");
        }
        repository.update(product);
    }

    public void deleteProduct(String id) throws Exception {
        if (repository.findById(id).isEmpty()) {
            throw new Exception("Product not found");
        }
        repository.delete(id);
    }
    // Función para validar que el formato de cantidad sea correcto
    private void validateQuantity(String quantity) throws Exception {
        String regex = "^[0-9]+\\s+[a-zA-Z]+$"; // Número seguido de texto, e.g., "5 gramos".
        if (!quantity.matches(regex)) {
            throw new Exception("Invalid quantity format. Use formats like '5 gramos', '10 piezas', etc.");
        }
    }
}

