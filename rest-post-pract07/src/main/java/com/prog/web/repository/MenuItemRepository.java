package com.prog.web.repository;

import com.prog.web.db.MenuItem;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface MenuItemRepository extends FullEntityRepository<MenuItem, Integer> {
    //Crea un método para buscar por categoría exacta
    List<MenuItem> findByCategory(String category);
    //Crea un método para buscar platillos según si son vegetarianos o no:
    List<MenuItem> findByIsVegetarian(Boolean isVegetarian);
}
