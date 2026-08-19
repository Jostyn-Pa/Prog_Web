package com.prog.web.servicios.impl;

import com.prog.web.db.Director;
import com.prog.web.db.Pelicula;
import com.prog.web.repository.DirectorRepository;
import com.prog.web.servicios.inter.DirectorServicioInter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DirectorServicioImpl implements DirectorServicioInter {

    private final DirectorRepository directorRepository;

    @Inject
    public DirectorServicioImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public List<Director> findAll() {
        return directorRepository.findAll();
    }

    @Override
    public Optional<Director> findById(Integer id) {
        return directorRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public Director save(Director director) {
        return directorRepository.save(director);
    }

    @Override
    @Transactional
    public Optional<Director> update(Integer id, Director director) {
        return directorRepository.findOptionalBy(id)
                .map(up -> {
                    director.setId(up.getId());
                    return directorRepository.save(director);
                });
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return directorRepository.findOptionalBy(id)
                .map(del -> {
                    directorRepository.remove(del);
                    return true;
                }).orElse(false);
    }

}
