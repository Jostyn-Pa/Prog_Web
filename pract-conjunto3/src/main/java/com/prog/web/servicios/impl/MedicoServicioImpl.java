package com.prog.web.servicios.impl;

import com.prog.web.db.Medico;
import com.prog.web.repository.MedicoRepository;
import com.prog.web.servicios.inter.MedicoServicioInter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MedicoServicioImpl implements MedicoServicioInter {

    private final MedicoRepository medicoRepository;

    @Inject
    public MedicoServicioImpl(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }

    @Override
    public Optional<Medico> findById(Integer id) {
        return medicoRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public Medico save(Medico medico) {

        // 1. PRIMERO validamos los datos de entrada
        if (medico.getNombre() == null || medico.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        if (medico.getEspecialidad() == null || medico.getEspecialidad().trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad no puede ser nula o vacía.");
        }

        // 2. LUEGO, si todo está correcto, lo guardamos en la BD
        return medicoRepository.save(medico);
    }

    @Override
    @Transactional
    public Optional<Medico> update(Integer id, Medico medico) {
        return medicoRepository.findOptionalBy(id)
                .map(up -> {
                    medico.setId(up.getId());
                    return medicoRepository.save(medico);
                });
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return medicoRepository.findOptionalBy(id)
                .map(del -> {
                    medicoRepository.remove(del);
                    return true;
                }).orElse(false);
    }
}
