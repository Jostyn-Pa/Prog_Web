package com.prog.web.servicios.impl;

import com.prog.web.db.Post;
import com.prog.web.db.Todo;
import com.prog.web.repositories.PostRepository;
import com.prog.web.repositories.TodoRepository;
import com.prog.web.servicios.interfaces.TodoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    @Inject
    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> findAll() { return todoRepository.findAll(); }

    @Override
    public Optional<Todo> findById(Integer id) { return todoRepository.findOptionalBy(id); }

    @Override
    @Transactional
    public void save(Todo todo) { todoRepository.save(todo); }

    @Override
    @Transactional
    public void update(Integer id, Todo todo) {
        todoRepository.findOptionalBy(id).ifPresent(existing -> {
            todo.setId(id);
            todoRepository.save(todo);
        });
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        todoRepository.findOptionalBy(id).ifPresent(todoRepository::remove);
    }
}