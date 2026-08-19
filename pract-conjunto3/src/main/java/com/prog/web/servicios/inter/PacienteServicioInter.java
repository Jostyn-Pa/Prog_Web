package com.prog.web.servicios.inter;

import com.prog.web.db.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteServicioInter {
    List<Paciente> findAll();
    Optional<Paciente> findById(Integer id);
    Paciente save(Paciente paciente);
    Optional<Paciente> update(Integer id, Paciente paciente);
    boolean delete(Integer id);
    List<Paciente> findByMedicoId(Integer id);
    List<Paciente> queryOpcionales(
            String nombre,
            String enfermedad,
            Integer edadMinima,
            Integer edadMaxima
    );
    List<Paciente> filtrosObligatorios(Double minCosto, Double maxCosto);
}
