package com.prog.web;

import com.prog.web.rest.ClienteRest;
import com.prog.web.rest.ExternoRest;
import com.prog.web.rest.OrdenCompraRest;
import com.prog.web.rest.ProductoRest;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Set;

@ApplicationPath("/api")
public class MyApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(
                ClienteRest.class,
                ExternoRest.class,
                OrdenCompraRest.class,
                ProductoRest.class
        );
    }
}
