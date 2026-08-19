package com.prog.web.servicios.interfaces;

import com.prog.web.db.Todo;
import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> findAll();
    Optional<Todo> findById(Integer id);
    void save(Todo todo);
    void update(Integer id, Todo todo);
    void delete(Integer id);
}