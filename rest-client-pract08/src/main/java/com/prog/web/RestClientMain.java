package com.prog.web;

import com.prog.web.dto.AuthorDto;
import com.prog.web.dto.BookDto;
import com.prog.web.inter.BookRest;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import java.util.List;

public class RestClientMain {
    static void main() {
        //RestEasyClient con BuildClient
        ResteasyClient client = (ResteasyClient) ClientBuilder.newClient();

        //Target
        ResteasyWebTarget target = client.target("http://localhost:8080");

        //Proxy
        BookRest proxy = target.proxy(BookRest.class);

        AuthorDto author = AuthorDto.builder().name("John").build();

        System.out.println("POST");
        BookDto book = BookDto.builder()
                .title("HOLA")
                .price(50.0)
                .author(author)
                .build();

        try (Response response = proxy.create(book)) {
            System.out.println(response.getStatus());
        }

        System.out.println("GET");
        List<BookDto> lista = proxy.findAll();
        lista.forEach(System.out::println);

        System.out.println("BYID");
        BookDto byId = proxy.findById(2);
        System.out.println(byId);

        client.close();
    }
}
