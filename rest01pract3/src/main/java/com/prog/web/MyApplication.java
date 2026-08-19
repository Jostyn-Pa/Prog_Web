package com.prog.web;

import com.prog.web.rest.ProductRest;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Set;

@ApplicationPath("/api")
public class MyApplication extends Application {

    public Set<Class<?>> getClasses() {
        return Set.of(
                ProductRest.class
        );
    }

}
