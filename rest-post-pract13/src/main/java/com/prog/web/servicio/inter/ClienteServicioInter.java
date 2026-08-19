package com.prog.web.servicio.inter;

import com.prog.web.db.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteServicioInter {
    List<Cliente> findAll();
    Optional<Cliente> findById(Integer id);
    Cliente save(Cliente cliente);
    Optional<Cliente> update(Integer id, Cliente cliente);
    boolean delete(Integer id);
}
