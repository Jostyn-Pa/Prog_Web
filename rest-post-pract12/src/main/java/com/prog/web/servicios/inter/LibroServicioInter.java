package com.prog.web.servicios.inter;

import com.prog.web.db.Libro;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface LibroServicioInter {
    List<Libro> findAll();
    Optional<Libro> findById(Integer id);
    Libro save(Libro libro);
    Optional<Libro> update(Integer id, Libro libro);
    boolean delete(Integer id);
    List<Libro> queryFilter(String genero, String nacionalidad);
}
