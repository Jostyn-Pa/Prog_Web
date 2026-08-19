package com.prog.web.repository;

import com.prog.web.db.Product;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends FullEntityRepository<Product, Integer> {
    //Buscar todos los productos que pertenezcan a una categoría específica.
    List<Product> findByCategory(String category);
    //Buscar productos cuyo nombre contenga una palabra (búsqueda parcial).
    List<Product> findByNameLike(String name);
    List<Product> findByStockLessThan(Integer stock);
    List<Product> findByStockGreaterThan(Integer stock);
}
