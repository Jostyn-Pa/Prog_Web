package com.prog.web.servicios.impl;

import com.prog.web.db.Pelicula;
import com.prog.web.repository.PeliculaRepository;
import com.prog.web.servicios.inter.PeliculaServicioInter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PeliculaServicioImpl implements PeliculaServicioInter {

    private final PeliculaRepository peliculaRepository;

    @Inject
    public PeliculaServicioImpl(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    @Override
    public List<Pelicula> findAll() {
        return peliculaRepository.findAll();
    }

    @Override
    public Optional<Pelicula> findById(Integer id) {
        return peliculaRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public Pelicula save(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    @Override
    @Transactional
    public Optional<Pelicula> update(Integer id, Pelicula pelicula) {
        return peliculaRepository.findOptionalBy(id)
                .map(up -> {
                    pelicula.setId(up.getId());
                    return peliculaRepository.save(pelicula);
                });
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return peliculaRepository.findOptionalBy(id)
                .map(del -> {
                    peliculaRepository.remove(del);
                    return true;
                }).orElse(false);
    }

    @Override
    public List<Pelicula> filtrosOpcionales(String titulo, String genero, Double minPrecio, Double maxPrecio, Integer anio) {
        return peliculaRepository.filtrosOpcionales(
                titulo, genero, minPrecio, maxPrecio, anio
        );
    }

    @Override
    public List<Pelicula> filtrosObligatorios(Double minPrecio, Double maxPrecio) {
        return peliculaRepository.filtrosObligatorios(minPrecio, maxPrecio);
    }

    @Override
    public List<Pelicula> findDirectorById(Integer id) {
        return peliculaRepository.findByDirectorId(id);
    }
}
