package com.prog.web.servicios.impl;

import com.prog.web.db.Libro;
import com.prog.web.repository.LibroRepository;
import com.prog.web.servicios.inter.LibroServicioInter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class LibroServicioImpl implements LibroServicioInter {

    private final LibroRepository libroRepository;

    @Inject
    public LibroServicioImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    @Override
    public Optional<Libro> findById(Integer id) {

        if(id == null)
            return Optional.empty();

        return libroRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public Libro save(Libro libro) {

        return libroRepository.save(libro);
    }

    @Override
    @Transactional
    public Optional<Libro> update(Integer id, Libro libro) {
        return libroRepository.findOptionalBy(id).map(up -> {
            libro.setId(up.getId());
            return libroRepository.save(libro);
        });
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return libroRepository.findOptionalBy(id).map(del -> {
            libroRepository.remove(del);
            return true;
        }).orElse(false);
    }

    @Override
    public List<Libro> queryFilter(String genero, String nacionalidad) {
        return libroRepository.filter(genero, nacionalidad);
    }
}
