package com.prog.web.servicios.inter;

import com.prog.web.db.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoServicioInter{
    List<Producto> findAll();
    Optional<Producto> findById(Integer id);
    Producto create(Producto producto);
    Optional<Producto> update(Integer id, Producto producto);
    boolean delete(Integer id);
    List<Producto> query(String nombre, Double minPrecio, Double maxPrecio);
}
