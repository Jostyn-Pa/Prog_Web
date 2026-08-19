package com.prog.web.servicios.impl;

import com.prog.web.db.OrdenCompra;
import com.prog.web.repository.OrdenCompraRepository;
import com.prog.web.servicios.inter.OrdenCompraServiceInter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class OrdenCompraServiceInterImpl implements OrdenCompraServiceInter {

    private final OrdenCompraRepository ordenCompraRepository;

    @Inject
    public OrdenCompraServiceInterImpl(OrdenCompraRepository ordenCompraRepository) {
        this.ordenCompraRepository = ordenCompraRepository;
    }

    @Override
    public List<OrdenCompra> findAll() {
        return ordenCompraRepository.findAll();
    }

    @Override
    public Optional<OrdenCompra> findById(Integer id) {
        return ordenCompraRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public OrdenCompra save(OrdenCompra ordenCompra) {
        return ordenCompraRepository.save(ordenCompra);
    }

    @Override
    @Transactional
    public Optional<OrdenCompra> update(Integer id, OrdenCompra ordenCompra) {
        return ordenCompraRepository.findOptionalBy(id).map(up -> {
            ordenCompra.setId(up.getId());
            return ordenCompraRepository.save(ordenCompra);
        });
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return ordenCompraRepository.findOptionalBy(id).map(del -> {
            ordenCompraRepository.remove(del);
            return true;
        }).orElse(false);
    }

    @Override
    public List<OrdenCompra> findByClienteId(Integer clienteId) {
        return ordenCompraRepository.findByClienteId(clienteId);
    }
}
