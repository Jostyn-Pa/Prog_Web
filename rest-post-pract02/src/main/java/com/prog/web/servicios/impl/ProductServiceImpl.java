package com.prog.web.servicios.impl;

import com.prog.web.db.Product;
import com.prog.web.repository.ProductRepository;
import com.prog.web.servicios.inter.ProductService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Inject
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> update(Integer id, Product product) {
        return productRepository.findOptionalBy(id).map(existing -> {
            product.setId(existing.getId());
            return productRepository.save(product);
        });
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return productRepository.findOptionalBy(id).map(ex -> {
            productRepository.remove(ex);
            return true;
        }).orElse(false);
    }

    @Override
    public List<Product> findByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return productRepository.findAll(); // Si no mandan nombre, devolvemos todo
        }
        // Agregamos los comodines para la búsqueda parcial
        return productRepository.findByNameLike("%" + name + "%");
    }

    @Override
    public List<Product> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> findByStockLessThan(Integer stock) {
        return productRepository.findByStockLessThan(stock);
    }

    @Override
    public List<Product> findByStockGreaterThan(Integer stock) {
        return productRepository.findByStockGreaterThan(stock);
    }
}
