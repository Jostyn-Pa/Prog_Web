package com.prog.web.repositories;

import com.prog.web.db.Todo;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface TodoRepository extends FullEntityRepository<Todo, Integer> {
}