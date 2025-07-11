package com.example.vaadin_test_example.client;

import com.example.vaadin_test_example.client.dto.BookDto;
import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.awt.print.Book;
import java.util.List;

@Service
public class ClientService {

    private final WebClient webClient;

    public ClientService(WebClient.Builder webClientBuilder)
    {
        this.webClient = webClientBuilder.baseUrl("http://localhost:9090/api").build();
    }

    public BookDto getBooksById(String id)
    {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("books/{id}").build(id))
                .retrieve()
                .bodyToMono(BookDto.class)
                .block();
    }
}
