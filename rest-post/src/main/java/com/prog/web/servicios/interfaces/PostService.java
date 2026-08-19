package com.prog.web.servicios.interfaces;

import com.prog.web.db.Post;
import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> findAll();
    Optional<Post> findById(Integer id);
    void save(Post post);
    void update(Integer id, Post post);
    void delete(Integer id);
}