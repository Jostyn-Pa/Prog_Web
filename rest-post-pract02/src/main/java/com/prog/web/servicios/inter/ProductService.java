package com.prog.web.servicios.inter;

import com.prog.web.db.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Integer id);
    Product save(Product product);
    Optional<Product> update(Integer id, Product product);
    boolean delete(Integer id);
    List<Product> findByName(String name);

    List<Product> findByCategory(String category);
    List<Product> findByStockLessThan(Integer stock);
    List<Product> findByStockGreaterThan(Integer stock);
}
