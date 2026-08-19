package com.prog.web.servicios.inter;

import com.prog.web.db.Autor;

import java.util.List;
import java.util.Optional;

public interface AutorServicioInter {
    List<Autor> findAll();
    Optional<Autor> findById(Integer id);
    Autor save(Autor autor);
    Optional<Autor> update(Integer id, Autor autor);
    boolean delete(Integer id);
}
