package com.prog.web;

import com.prog.web.dto.UserDto;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.util.List;

public class ClienteRestMain {

    public static final String USERS_URL = "http://localhost:8080/api/users";

    public static void main(String[] args) {

        try (var client = ClientBuilder.newClient()) {

            System.out.println("--- GET (Un usuario) ---");
            UserDto user1 = client.target(USERS_URL)
                    .path("/{id}")
                    .resolveTemplate("id", 2)
                    .request(MediaType.APPLICATION_JSON)
                    .get(UserDto.class);
            System.out.println(user1);


            System.out.println("\n--- GET (Todos los usuarios) ---");
            List<UserDto> users = client.target(USERS_URL)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<UserDto>>() {});
            System.out.println("Se encontraron " + users.size() + " usuarios.");
            System.out.println("--- GET (Todos los usuarios) ---");
            System.out.println(users);


            System.out.println("\n--- POST (Crear usuario) ---");
            var newUser = new UserDto();
            newUser.setName("John Doe");
            newUser.setUsername("johndoe123");
            newUser.setEmail("john.doe@example.com");
            // Dirección
            newUser.setAddressStreet("Kulas Light");
            newUser.setAddressSuite("Apt. 556");
            newUser.setAddressCity("Gwenborough");
            newUser.setAddressZipcode("92998-3874");
            // Geolocalización
            newUser.setAddressGeoLat(new java.math.BigDecimal("-37.3159"));
            newUser.setAddressGeoLng(new java.math.BigDecimal("81.1496"));
            newUser.setPhone("1-770-736-8031 x56442");
            newUser.setWebsite("hildegard.org");
            newUser.setCompanyName("Romaguera-Crona");
            newUser.setCompanyCatchPhrase("Multi-layered client-server neural-net");
            newUser.setCompanyBs("harness real-time e-markets");

            var responsePost = client.target(USERS_URL)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(newUser, MediaType.APPLICATION_JSON));
            System.out.println("Status POST: " + responsePost.getStatus()); // Debería ser 201


            System.out.println("\n--- PUT (Actualizar usuario) ---");
            // Actualizaremos el usuario 2 que buscamos al principio
            if (user1 != null) {
                user1.setName("Ervin Howell Actualizado"); // Cambiamos el nombre

                var responsePut = client.target(USERS_URL)
                        .path("/{id}")
                        .resolveTemplate("id", 2)
                        .request(MediaType.APPLICATION_JSON)
                        .put(Entity.entity(user1, MediaType.APPLICATION_JSON));
                System.out.println("Status PUT: " + responsePut.getStatus()); // Debería ser 200
            }


            System.out.println("\n--- DELETE (Eliminar usuario) ---");
            // Eliminaremos el usuario con ID 3
            var responseDelete = client.target(USERS_URL)
                    .path("/{id}")
                    .resolveTemplate("id", 3)
                    .request()
                    .delete();
            System.out.println("Status DELETE: " + responseDelete.getStatus()); // Debería ser 204

        } catch (Exception e) {
            System.err.println("ERROR: No se pudo conectar. ¿Seguro que el servidor del otro proyecto está encendido?");
            e.printStackTrace();
        }
    }
}