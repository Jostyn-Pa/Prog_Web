package com.prog.web.servicios.impl;

import com.prog.web.db.Autor;
import com.prog.web.repository.AutorRepository;
import com.prog.web.servicios.inter.AutorServicioInter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AutorServicioImpl implements AutorServicioInter {

    private final AutorRepository autorRepository;

    @Inject
    public AutorServicioImpl(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    @Override
    public Optional<Autor> findById(Integer id) {
        return autorRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public Autor save(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    @Transactional
    public Optional<Autor> update(Integer id, Autor autor) {
        return autorRepository.findOptionalBy(id)
                .map(up -> {
                    autor.setNombre(autor.getNombre());
                    return autorRepository.save(autor);
                });
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return autorRepository.findOptionalBy(id).map(del -> {
            autorRepository.remove(del);
            return true;
        }).orElse(false);
    }
}
