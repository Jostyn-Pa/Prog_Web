package com.prog.web.servicios.impl;

import com.prog.web.db.Post;
import com.prog.web.repository.PostRepository;
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
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Integer id) {
        return postRepository.findOptionalBy(id);
    }

    @Override
    @Transactional
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public Optional<Post> update(Integer id, Post post) {
        return postRepository.findOptionalBy(id).map(existingPost -> {
            post.setId(existingPost.getId());
            return postRepository.save(post);
        });
    }

    @Override
    @Transactional
    public boolean delete(Integer id) {
        return postRepository.findOptionalBy(id).map(ex -> {
            postRepository.remove(ex);
            return true;
        }).orElse(false);
    }

    @Override
    public List<Post> searchPosts(String author, String title) {
        // Si el autor NO es nulo y NO está vacío
        if (author != null && !author.trim().isEmpty()) {
            return postRepository.findByAuthorName(author);
        }
        // Si el título NO es nulo y NO está vacío
        else if (title != null && !title.trim().isEmpty()) {
            // Agregamos los comodines para que busque coincidencias parciales
            return postRepository.findByTitleLike("%" + title + "%");
        }
        // Si no enviaron ninguno de los dos, devolvemos todo (o podrías devolver una lista vacía)
        else {
            return postRepository.findAll();
        }
    }
}
