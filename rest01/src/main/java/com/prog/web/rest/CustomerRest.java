package com.prog.web.rest;

import com.prog.web.dto.Customer;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;

@Path("/customers")
//Lo mas simple es anotarle a la clase de tipo JSON
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class CustomerRest {

    private static Map<Integer, Customer> customer = Map.of(
            1, Customer.builder().id(1).name("cliente 1").direccion("dir1").build(),
            2, Customer.builder().id(2).name("cliente 2").direccion("dir2").build(),
            3, Customer.builder().id(3).name("cliente 3").direccion("dir3").build(),
            4, Customer.builder().id(4).name("cliente 4").direccion("dir4").build(),
            5, Customer.builder().id(5).name("cliente 5").direccion("dir5").build()
    );


//    @GET
//    @Path("/{id}")
//    //Va a enviar algo el servidor en formato json
//    @Produces("application/json")
//    public String findById(@PathParam("id") Integer id) {
//        var customer = CustomerRest.customer.get(id);
//
//        if (customer == null) {
//            return "No se encontro el cliente";
//        }
//
//        String json = """
//                {
//                    "id": %d,
//                    "name": %s,
//                    "direccion": %s,
//                }
//                """.formatted(
//                customer.getId(),
//                customer.getName(),
//                customer.getDireccion()
//
//        );
//        return json;
//
//    }

    @GET
    @Path("/{id}")
    //Va a enviar algo el servidor en formato json
    //@Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Integer id) {
        //No se puede cambiar directamente de un objeto a un json
        //Es por eso que usamos rest easy para que pase automatico
        var obj = CustomerRest.customer.get(id);
        if (obj == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        //Se puede retornar al cliente una cabecera
        //return Response.ok(obj).header("mi cabecera","12314").build();

        return Response.ok(obj).build();

    }

    @POST
    //@Consumes(MediaType.APPLICATION_JSON)
    public void save(@HeaderParam("Content-Type")String contentType, Customer obj) {
        System.out.println("*****"+contentType);
        System.out.println(obj);

    }
}
