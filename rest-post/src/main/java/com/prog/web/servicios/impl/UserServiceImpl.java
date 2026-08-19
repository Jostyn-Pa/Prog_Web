package com.prog.web.servicios.impl;

import com.prog.web.repositories.UserRepository;
import com.prog.web.servicios.interfaces.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    final UserRepository userRepository;

    @Inject
    public UserServiceImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
