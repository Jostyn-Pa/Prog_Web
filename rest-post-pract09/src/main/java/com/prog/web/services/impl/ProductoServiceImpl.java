package com.prog.web.services.impl;

import com.prog.web.db.Producto;
import com.prog.web.repository.ProductoRepository;
import com.prog.web.services.inter.ProductoServiceInter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductoServiceImpl implements ProductoServiceInter {

    private final ProductoRepository productoRepository;

    @Inject
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        return productoRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public Producto save(Producto cliente) {
        return productoRepository.save(cliente);
    }

    @Override
    @Transactional
    public Optional<Producto> update(Integer id, Producto producto) {
        return productoRepository.findOptionalBy(id).map(up -> {
            producto.setId(up.getId());
            return productoRepository.save(producto);
        });
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return productoRepository.findOptionalBy(id).map(del -> {
            productoRepository.remove(del);
            return true;
        }).orElse(false);
    }
}
