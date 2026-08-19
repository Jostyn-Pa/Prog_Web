package com.prog.web;

import com.prog.web.cors.CorsFilter;
import com.prog.web.rest.MedicoRest;
import com.prog.web.rest.PacienteRest;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Set;

@ApplicationPath("/api")
public class MyApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(
                MedicoRest.class,
                PacienteRest.class,
                CorsFilter.class
        );
    }
}
