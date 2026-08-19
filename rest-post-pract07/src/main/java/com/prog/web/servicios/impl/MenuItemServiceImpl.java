package com.prog.web.servicios.impl;

import com.prog.web.db.MenuItem;
import com.prog.web.repository.MenuItemRepository;
import com.prog.web.servicios.inter.MenuItemService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;

    @Inject
    public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public List<MenuItem> findAll() {
        return menuItemRepository.findAll();
    }

    @Override
    public Optional<MenuItem> findById(Integer id) {
        return menuItemRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public MenuItem save(MenuItem menuItem) {
        return menuItemRepository.save(menuItem);
    }

    @Override
    @Transactional
    public Optional<MenuItem> update(Integer id, MenuItem menuItem) {
        return menuItemRepository.findOptionalBy(id).map(up -> {
            menuItem.setId(up.getId());
            return menuItemRepository.save(menuItem);
        });
    }

    @Override
    @Transactional
    public Boolean delete(Integer id) {
        return menuItemRepository.findOptionalBy(id).map(del -> {
            menuItemRepository.remove(del);
            return true;
        }).orElse(false);
    }

    @Override
    public List<MenuItem> findByCategory(String category) {
        return menuItemRepository.findByCategory(category);
    }

    @Override
    public List<MenuItem> findByIsVegetarian(Boolean isVegetarian) {
        return menuItemRepository.findByIsVegetarian(isVegetarian);
    }
}
