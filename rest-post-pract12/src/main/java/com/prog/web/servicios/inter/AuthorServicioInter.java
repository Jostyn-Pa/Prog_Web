package com.prog.web.servicios.inter;

import com.prog.web.db.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorServicioInter {
    List<Author> findAll();
    Optional<Author> findById(Integer id);
    Author save(Author author);
    Optional<Author> update(Integer id, Author author);
    boolean delete(Integer id);
}
