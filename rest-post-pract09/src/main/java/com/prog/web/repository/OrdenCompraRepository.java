package com.prog.web.repository;

import com.prog.web.db.OrdenCompra;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface OrdenCompraRepository extends FullEntityRepository<OrdenCompra, Integer> {
    // "Busca todas las órdenes donde el id del cliente coincida con este valor"
    List<OrdenCompra> findByClienteId(Integer clienteid);
}
