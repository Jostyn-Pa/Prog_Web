package com.prog.web.servicios.impl;

import com.prog.web.db.Post;
import com.prog.web.repositories.PostRepository;
import com.prog.web.servicios.interfaces.PostService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Inject
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> findAll() { return postRepository.findAll(); }

    @Override
    public Optional<Post> findById(Integer id) { return postRepository.findOptionalBy(id); }

    @Override
    @Transactional
    public void save(Post post) { postRepository.save(post); }

    @Override
    @Transactional
    public void update(Integer id, Post post) {
        postRepository.findOptionalBy(id).ifPresent(existing -> {
            post.setId(id);
            postRepository.save(post);
        });
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        postRepository.findOptionalBy(id).ifPresent(postRepository::remove);
    }
}