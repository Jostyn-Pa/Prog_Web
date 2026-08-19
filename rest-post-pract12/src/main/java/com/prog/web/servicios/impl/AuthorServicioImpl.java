package com.prog.web.servicios.impl;

import com.prog.web.db.Author;
import com.prog.web.repository.AuthorRepository;
import com.prog.web.servicios.inter.AuthorServicioInter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AuthorServicioImpl implements AuthorServicioInter {

    private final AuthorRepository authorRepository;

    @Inject
    public AuthorServicioImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Integer id) {
        if(id == null) {
            return Optional.empty();
        }
        return authorRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public Optional<Author> update(Integer id, Author author) {
        return authorRepository.findOptionalBy(id).map(up -> {
            author.setId(up.getId());
            return authorRepository.save(author);
        });
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return authorRepository.findOptionalBy(id).map(del -> {
            authorRepository.remove(del);
            return true;
        }).orElse(false);
    }
}
