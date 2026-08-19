package com.prog.web.services.inter;

import com.prog.web.db.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoServiceInter {
    List<Producto> findAll();
    Optional<Producto> findById(Integer id);
    Producto save(Producto cliente);
    Optional<Producto> update(Integer id, Producto producto);
    boolean delete(Integer id);
}
