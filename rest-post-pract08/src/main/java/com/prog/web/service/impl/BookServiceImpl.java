package com.prog.web.service.impl;

import com.prog.web.db.Book;
import com.prog.web.repository.BookRepository;
import com.prog.web.service.inter.BookServiceInter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookServiceImpl implements BookServiceInter {

    private final BookRepository  bookRepository;

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
        return bookRepository.findOptionalBy(id).map(up -> {
            book.setId(up.getId());
            return  bookRepository.save(book);
        });
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return bookRepository.findOptionalBy(id).map(del -> {
            bookRepository.remove(del);
            return true;
        }).orElse(false);
    }
}
