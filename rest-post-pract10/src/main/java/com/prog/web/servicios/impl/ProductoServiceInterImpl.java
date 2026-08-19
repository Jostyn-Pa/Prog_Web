package com.prog.web.servicios.impl;

import com.prog.web.db.Producto;
import com.prog.web.repository.OrdenCompraRepository;
import com.prog.web.repository.ProductoRepository;
import com.prog.web.servicios.inter.ProductoServiceInter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductoServiceInterImpl implements ProductoServiceInter {

    private final ProductoRepository productoRepository;

    @Inject
    public ProductoServiceInterImpl(ProductoRepository productoRepository) {
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
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
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

    @Override
    public List<Producto> query(String nombre, Double minPrecio, Double maxPrecio) {
        return productoRepository.query(nombre, minPrecio, maxPrecio);
    }
}
