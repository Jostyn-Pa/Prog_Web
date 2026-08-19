package com.prog.web.service.inter;

import com.prog.web.db.Book;

import java.util.List;
import java.util.Optional;

public interface BookServiceInter {
    List<Book> findAll();
    Optional<Book> findById(Integer id);
    Book save(Book book);
    Optional<Book> update(Integer id, Book book);
    boolean delete(Integer id);
}
