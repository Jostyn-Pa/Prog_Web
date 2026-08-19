package com.prog.web;

import com.prog.web.dto.MenuItemDto;
import com.prog.web.inter.MenuItemRest;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.lang.reflect.Proxy;
import java.util.List;

public class RestClienteMain {
    static void main() {
        //RestEasyClient con ClienteBuilder
        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();

        //Target
        ResteasyWebTarget target = client.target("http://localhost:8080");
        //Proxy
        MenuItemRest proxy = target.proxy(MenuItemRest.class);
        /*
        Acción 1: Crea un nuevo platillo en Java (Ej: Tiramisú, Postre, 5.50, true),
        envíalo por POST usando tu proxy, e imprime el status.
         */

        System.out.println("POST");
        MenuItemDto menuItemDto = MenuItemDto.builder()
                .name("Tiramisú")
                .category("Postre")
                .price(5.50)
                .isVegetarian(true)
                .build();
        try (Response response = proxy.save(menuItemDto)) {
            System.out.println(response.getStatus());
        }

        /*
        Acción 2: Usa tu proxy para llamar al endpoint /veggie pidiendo específicamente
        los platillos vegetarianos (true). Imprime sus nombres y precios usando un ciclo for.
         */
        List<MenuItemDto> vegetarianos = proxy.findByIsVegetarian(true);
        vegetarianos.forEach(System.out::println);

        client.close();
    }
}
