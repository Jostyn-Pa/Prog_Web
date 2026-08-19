package com.prog.web;

import com.prog.web.dto.WorkoutLogDto;
import com.prog.web.inter.WorkoutRestClient;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.lang.reflect.Proxy;

public class ClientRestMain {
    static void main() {
        //RestEasyCliente u CLientBuilder
        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();
        //target
        ResteasyWebTarget target = client.target("http://localhost:8080");
        //proxy
        WorkoutRestClient proxy = target.proxy(WorkoutRestClient.class);


        System.out.println("creando");
        WorkoutLogDto workoutLogDto = WorkoutLogDto.builder()
                .exerciseName("asd")
                .splitType("ds")
                .weight(8.2)
                .reps(2)
                .build();
        try(Response response = proxy.create(workoutLogDto)){
            System.out.println(response.getStatus());
        }
        client.close();
    }
}
