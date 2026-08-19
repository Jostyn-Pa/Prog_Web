package com.prog.web.servicios.inter;

import com.prog.web.db.OrdenCompra;

import java.util.List;
import java.util.Optional;

public interface OrdenCompraServiceInter {
    List<OrdenCompra> findAll();
    Optional<OrdenCompra> findById(Integer id);
    OrdenCompra save(OrdenCompra ordenCompra);
    Optional<OrdenCompra> update(Integer id, OrdenCompra ordenCompra);
    boolean delete(Integer id);
    List<OrdenCompra> findByClienteId(Integer id);
}
