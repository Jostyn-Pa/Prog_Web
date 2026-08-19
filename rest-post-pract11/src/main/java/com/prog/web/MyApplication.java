package com.prog.web;

import com.prog.web.rest.CursoRest;
import com.prog.web.rest.InstructoresExternoRest;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Set;

@ApplicationPath("/api")
public class MyApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(
                CursoRest.class,
                InstructoresExternoRest.class
        );
    }
}
