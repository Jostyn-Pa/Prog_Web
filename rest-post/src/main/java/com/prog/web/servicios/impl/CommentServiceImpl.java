package com.prog.web.servicios.impl;

import com.prog.web.db.Comment;
import com.prog.web.repositories.CommentRepository;
import com.prog.web.servicios.interfaces.CommentService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Inject
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Integer id) {
        return commentRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void update(Integer id, Comment comment) {
        commentRepository.findOptionalBy(id).ifPresent(existing -> {
            comment.setId(id);
            commentRepository.save(comment);
        });
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        commentRepository.findOptionalBy(id).ifPresent(commentRepository::remove);
    }
}