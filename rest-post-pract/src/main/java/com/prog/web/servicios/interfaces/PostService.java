package com.prog.web.servicios.interfaces;

import com.prog.web.db.Post;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> findAll();
    Optional<Post> findById(Integer id);
    Post save (Post post);
    Optional<Post> update(Integer id, Post post);
    boolean delete(Integer id);
    List<Post> searchPosts(String author, String title);
}
