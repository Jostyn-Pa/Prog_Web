package com.prog.web.repositories;

import com.prog.web.db.Comment;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface CommentRepository extends FullEntityRepository<Comment, Integer> {
}