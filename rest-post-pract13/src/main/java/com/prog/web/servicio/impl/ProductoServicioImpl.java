package com.prog.web.servicio.impl;

import com.prog.web.db.Producto;
import com.prog.web.repository.ProductoRepository;
import com.prog.web.servicio.inter.ProductoServicioInter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductoServicioImpl implements ProductoServicioInter {

    private final ProductoRepository productoRepository;

    @Inject
    public ProductoServicioImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findById(Integer id) {
        if(id == null){
            return Optional.empty();
        }
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
        return productoRepository.findOptionalBy(id)
                .map(del -> {
                    productoRepository.remove(del);
                    return true;
                }).orElse(false);
    }

    @Override
    public List<Producto> findByNombreAndminPrecioAndMaxPrecio(String nombre, Double minPrecio, Double maxPrecio) {
        return productoRepository.findByNameAndByMinPriceAndMaxPrice(nombre, minPrecio, maxPrecio);
    }
}
