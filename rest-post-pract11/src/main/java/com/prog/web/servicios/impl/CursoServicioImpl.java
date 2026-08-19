package com.prog.web.servicios.impl;

import com.prog.web.db.Curso;
import com.prog.web.repository.CursoRepository;
import com.prog.web.servicios.inter.CursoServicioInter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.QueryParam;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CursoServicioImpl implements CursoServicioInter {

    private final CursoRepository cursoRepository;

    @Inject
    public CursoServicioImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    @Override
    public Optional<Curso> findById(Integer id) {
        return cursoRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    @Transactional
    public Optional<Curso> update(Integer id, Curso curso) {
        return cursoRepository.findOptionalBy(id).map(up -> {
            curso.setId(up.getId());
            return cursoRepository.save(curso);
        });
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return cursoRepository.findOptionalBy(id).map(del -> {
            cursoRepository.remove(del);
            return true;
        }).orElse(false);
    }

    @Override
    public List<Curso> searchQuery(@QueryParam("cat") String categoria, @QueryParam("precioMax") Double precioMaximo) {
        return cursoRepository.searchQuery(categoria, precioMaximo);
    }
}
