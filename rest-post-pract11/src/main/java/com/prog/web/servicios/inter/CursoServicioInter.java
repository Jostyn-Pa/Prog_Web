package com.prog.web.servicios.inter;

import com.prog.web.db.Curso;
import org.apache.deltaspike.data.api.QueryParam;

import java.util.List;
import java.util.Optional;

public interface CursoServicioInter {
    List<Curso> findAll();
    Optional<Curso> findById(Integer id);
    Curso save(Curso curso);
    Optional<Curso> update(Integer id, Curso curso);
    boolean delete(Integer id);
    List<Curso> searchQuery(String categoria, Double precioMaximo);
}
