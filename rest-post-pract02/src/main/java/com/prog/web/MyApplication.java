package com.prog.web;

import com.prog.web.rest.ProductRest;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.List;
import java.util.Set;

@ApplicationPath("/productos")

public class MyApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(ProductRest.class);
    }
}
