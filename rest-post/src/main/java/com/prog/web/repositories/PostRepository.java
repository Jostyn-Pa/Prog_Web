package com.prog.web.repositories;

import com.prog.web.db.Post;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface PostRepository extends FullEntityRepository<Post, Integer> {
    // DeltaSpike te permite buscar por métodos nombrados.
    // Por ejemplo, esto buscaría todos los posts de un usuario específico:
    // List<Post> findByUserId(Integer userId);
}