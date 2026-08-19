package com.prog.web;

import com.prog.web.db.OrdenCompra;
import com.prog.web.rest.OrdenCompraRest;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Set;

@ApplicationPath("/api")
public class MyApplication extends Application
{
    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(
                OrdenCompraRest.class
        );
    }
}
