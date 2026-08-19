package com.prog.web.repository;

import com.prog.web.db.OrdenCompra;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface OrdenCompraRepository extends FullEntityRepository<OrdenCompra, Integer> {
    List<OrdenCompra> findByClienteId(Integer id);
}
