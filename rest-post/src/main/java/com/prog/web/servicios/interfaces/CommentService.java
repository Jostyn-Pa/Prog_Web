package com.prog.web.servicios.interfaces;

import com.prog.web.db.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> findAll();
    Optional<Comment> findById(Integer id);
    void save(Comment comment);
    void update(Integer id, Comment comment);
    void delete(Integer id);
}