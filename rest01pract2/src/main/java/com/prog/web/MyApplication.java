package com.prog.web;

import com.prog.web.rest.TaskRest;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Application;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationPath("/api")
public class MyApplication extends Application {
    public Set<Class<?>> getClasses() {
        return Set.of(
                TaskRest.class
        );
    }
}
