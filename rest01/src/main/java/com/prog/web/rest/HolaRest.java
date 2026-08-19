package com.prog.web.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;

import java.time.LocalDateTime;

//Va en jerarquias application->clase-metodo
//Se ingresa mediante http:8080/api/hola/.....
//El path puede ir en la clase, o en el metodo tambien

@Path("/hola")
public class HolaRest {

    @GET
    @Path("/mundo")
    public String hola () {
        return "Hola Mundo, la hora es: " + LocalDateTime.now();
    }

    @GET
    @Path("/mundo2/{nombre}/{apellido}")
    //Se puede poner los params que se vean necesarios, el del Path param debe coincidir con el del path
    public String hola2(@PathParam("nombre") String x, @PathParam("apellido") String y) {
        return "Hola mi nombre es %s y mi apellido es %s ---> %s".formatted(x,y, LocalDateTime.now().toString());
    }

    //http:8080/api/hola/mundo3 ? nombre=diego & apellido=osorio
    //?= inicio
    //&= separador
    @GET
    @Path("/mundo3")
    //Se puede poner los params que se vean necesarios, el del Path param debe coincidir con el del path
    public String hola3(
            @QueryParam("nombre")String nombre,
            @QueryParam("apellido")@DefaultValue("No tiene apellido") String apellido){
        return "Hola mi nombre es %s y mi apellido es %s ---> %s".formatted(nombre,apellido,LocalDateTime.now().toString());
    }

    //Esto es para pasarle la manera en la cual se va a ordenar las personas o alguna cosa
    //Esto tiene sentido para ponerle como query param la manera en la cual se va a ordenar
    @GET
    @Path("/personas")
    public String listarPersonas(@QueryParam("sort")String sort){
        return "Listar personas ordenadas por: " +sort;
    }


    //Tratar de EVITAR usar el context
    @GET
    @Path("/mundo4")
    public String hola4(@Context HttpServletRequest request){
        var host=request.getHeader("Host");
        return "Hola 4 :"+host;
    }

}
