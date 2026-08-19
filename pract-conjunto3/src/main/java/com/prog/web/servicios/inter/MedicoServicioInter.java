package com.prog.web.servicios.inter;

import com.prog.web.db.Medico;

import java.util.List;
import java.util.Optional;

public interface MedicoServicioInter {
    List<Medico> findAll();
    Optional<Medico> findById(Integer id);
    Medico save(Medico medico);
    Optional<Medico> update(Integer id, Medico medico);
    boolean delete(Integer id);
}
