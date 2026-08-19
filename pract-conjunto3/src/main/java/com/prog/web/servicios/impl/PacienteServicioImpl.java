package com.prog.web.servicios.impl;

import com.prog.web.db.Paciente;
import com.prog.web.repository.PacienteRepository;
import com.prog.web.servicios.inter.PacienteServicioInter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PacienteServicioImpl implements PacienteServicioInter {

    private final PacienteRepository pacienteRepository;

    @Inject
    public PacienteServicioImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> findById(Integer id) {
        return pacienteRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public Paciente save(Paciente paciente) {
        if(paciente.getNombre() == null || paciente.getNombre().isEmpty()){
            throw new RuntimeException("El nombre del paciente no puede estar vació");
        } else if (paciente.getEnfermedad() == null || paciente.getEnfermedad().isEmpty()){
            throw new RuntimeException("La enfermedad del paciente no puede ser nula");
        } else if (paciente.getEdad() == null || paciente.getEdad() <= 0){
            throw new RuntimeException("La edad del paciente no puede ser nula");
        } else if (paciente.getMedico().getNombre() == null || paciente.getMedico().getNombre().isEmpty()){
            throw new RuntimeException("Campos no pueden ser nulos");
        }
        return pacienteRepository.save(paciente);
    }

    @Override
    @Transactional
    public Optional<Paciente> update(Integer id, Paciente paciente) {
        return pacienteRepository.findOptionalBy(id)
                .map(up -> {
                    paciente.setId(up.getId());
                    return pacienteRepository.save(paciente);
                });
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return pacienteRepository.findOptionalBy(id)
                .map(del -> {
                    pacienteRepository.remove(del);
                    return true;
                }).orElse(false);
    }

    @Override
    public List<Paciente> findByMedicoId(Integer id) {
        return pacienteRepository.findByMedicoId(id);
    }

    @Override
    public List<Paciente> queryOpcionales(String nombre, String enfermedad, Integer edadMinima, Integer edadMaxima) {
        return pacienteRepository.filtrosOpcionales(
                nombre, enfermedad, edadMinima, edadMaxima
        );
    }

    @Override
    public List<Paciente> filtrosObligatorios(Double minCosto, Double maxCosto) {
        if(minCosto == null || maxCosto == null){
            throw new RuntimeException("La costo del paciente no puede ser nula");
        }
        var obj = pacienteRepository.filtroObligatorios(minCosto, maxCosto);
        return obj;
    }
}
