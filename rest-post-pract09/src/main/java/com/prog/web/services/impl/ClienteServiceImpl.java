package com.prog.web.services.impl;

import com.prog.web.db.Cliente;
import com.prog.web.repository.ClienteRepository;
import com.prog.web.services.inter.ClienteServiceInter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClienteServiceImpl implements ClienteServiceInter {

    private final ClienteRepository clienteRepository;

    @Inject
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        return clienteRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public Optional<Cliente> update(Integer id, Cliente cliente) {
        return clienteRepository.findOptionalBy(id).map(up -> {
            cliente.setId(up.getId());
            return clienteRepository.save(cliente);
        });
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return clienteRepository.findOptionalBy(id).map(del -> {
            clienteRepository.remove(del);
            return true;
        }).orElse(false);
    }
}
