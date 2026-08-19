package com.prog.web.repository;

import com.prog.web.db.Post;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface PostRepository extends FullEntityRepository<Post, Integer> {
    List<Post> findByAuthorName(String authorName);
    List<Post> findByTitleLike(String title);
}
