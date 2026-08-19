package com.prog.web.servicios.inter;

import com.prog.web.db.Director;
import com.prog.web.db.Pelicula;

import java.util.List;
import java.util.Optional;

public interface DirectorServicioInter {
    List<Director> findAll();
    Optional<Director> findById(Integer id);
    Director save(Director director);
    Optional<Director> update(Integer id, Director director);
    boolean delete(Integer id);
}
