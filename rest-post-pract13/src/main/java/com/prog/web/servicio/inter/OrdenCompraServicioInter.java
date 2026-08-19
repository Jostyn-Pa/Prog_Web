package com.prog.web.servicio.inter;

import com.prog.web.db.OrdenCompra;

import java.util.List;
import java.util.Optional;

public interface OrdenCompraServicioInter {
    List<OrdenCompra> findAll();
    Optional<OrdenCompra> findById(Integer id);
    OrdenCompra save(OrdenCompra ordenCompra);
    Optional<OrdenCompra> update(Integer id, OrdenCompra ordenCompra);
    boolean delete(Integer id);
    List<OrdenCompra> findByClienteId(Integer id);
}
