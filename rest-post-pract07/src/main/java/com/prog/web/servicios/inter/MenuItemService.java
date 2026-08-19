package com.prog.web.servicios.inter;

import com.prog.web.db.MenuItem;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface MenuItemService{
    List<MenuItem> findAll();
    Optional<MenuItem> findById(Integer id);
    MenuItem save(MenuItem menuItem);
    Optional<MenuItem> update(Integer id, MenuItem menuItem);
    Boolean delete(Integer id);
    List<MenuItem> findByCategory(String category);
    List<MenuItem> findByIsVegetarian(Boolean isVegetarian);
}
