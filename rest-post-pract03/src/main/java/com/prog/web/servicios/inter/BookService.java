package com.prog.web.servicios.inter;

import com.prog.web.db.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Integer id);
    Book save(Book book);
    Optional<Book> update(Integer id, Book book);
    Boolean delete(Integer id);
    List<Book> findByTitleLike(String title);
    List<Book> findByPublishedYearGreaterThan(Integer year);
    List<Book> findByIsAvailable(Boolean status);
}
