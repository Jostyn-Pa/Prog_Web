package com.prog.web.servicios.impl;

import com.prog.web.db.Book;
import com.prog.web.repository.BookRepository;
import com.prog.web.servicios.inter.BookService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Inject
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Integer id) {
        return bookRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Optional<Book> update(Integer id, Book book) {
        return bookRepository.findOptionalBy(id).map(existing -> {
            book.setId(existing.getId());
            return bookRepository.save(book);
        });
    }

    @Override
    @Transactional
    public Boolean delete(Integer id) {
        return bookRepository.findOptionalBy(id).map(ex -> {
            bookRepository.remove(ex);
            return true;
        }).orElse(false);
    }

    @Override
    public List<Book> findByTitleLike(String title) {

        if(title == null || title.trim().isEmpty()) {
            return bookRepository.findAll();
        }
        return bookRepository.findByTitleLike("%" + title + "%");
    }

    @Override
    public List<Book> findByPublishedYearGreaterThan(Integer year) {
        return bookRepository.findByPublishedYearGreaterThan(year);
    }

    @Override
    public List<Book> findByIsAvailable(Boolean status) {
        return bookRepository.findByIsAvailable(status);
    }
}
