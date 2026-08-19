package com.prog.web.rest;

import com.prog.web.dto.Product;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Path("/products")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

public class ProductRest {
    private static final Map<Integer, Product> products = new ConcurrentHashMap<>(Map.of(
       1, Product.builder().id(1).name("laptop").category("electronics").price(1000.0).build(),
       2, Product.builder().id(2).name("tv").category("we").price(500.2).build()
    ));

    @POST
    public Response saveProduct(Product product) {
        if (product == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        products.put(product.getId(), product);
        return Response.status(Response.Status.CREATED).entity(product).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllProducts() {

        var obj = products.values();

        if (products.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.ok(obj).build();
        }
    }

    @GET
    @Path("/{id: \\d+}")
    public Response getProductById(@PathParam("id") Integer id) {
        var obj = products.get(id);
        if(obj == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(obj).build();
    }

    @GET
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findByCategory(@QueryParam("category") String category0) {
        var obj = products.values();

        if(products.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        var categoyFiltered = obj.stream().filter(p -> p.getCategory().equalsIgnoreCase(category0)).toList();

        return Response.ok(categoyFiltered).build();
    }
}
