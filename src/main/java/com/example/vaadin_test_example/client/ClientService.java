package com.example.vaadin_test_example.client;

import com.example.vaadin_test_example.client.dto.AuthorDto;
import com.example.vaadin_test_example.client.dto.BookDto;
import com.example.vaadin_test_example.util.RestResponsePage;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
                .uri(uriBuilder -> uriBuilder.path("/books/{id}").build(id))
                .retrieve()
                .bodyToMono(BookDto.class)
                .block();
    }

    public List<BookDto> getBooksByAuthor(int authorId)
    {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/books/author/{authorId}").build(authorId))
                .retrieve()
                .bodyToFlux(BookDto.class)
                .collectList()
                .block();
    }

    public RestResponsePage<BookDto> getAllBooks(int pageNumber, int pageLimit)
    {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/books")
                        .queryParam("page", pageNumber)
                        .queryParam("limit", pageLimit)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<RestResponsePage<BookDto>>() {})
                .block();
    }

    public List<BookDto> getBookByTitle(String title)
    {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/books/find")
                        .queryParam("title", title)
                        .build())
                .retrieve()
                .bodyToFlux(BookDto.class)
                .collectList()
                .block();
    }

    public List<BookDto> getLast10Books()
    {
        return webClient.get()
                .uri("/books/last10")
                .retrieve()
                .bodyToFlux(BookDto.class)
                .collectList()
                .block();
    }

    public RestResponsePage<AuthorDto> getAllAuthors(int pageNumber, int pageLimit)
    {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/authors")
                        .queryParam("page", pageNumber)
                        .queryParam("limit", pageLimit)
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<RestResponsePage<AuthorDto>>() {})
                .block();
    }

    public List<AuthorDto> getAuthorByFirstName(String firstName)
    {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/authors/find/firstname")
                        .queryParam("firstName", firstName)
                        .build())
                .retrieve()
                .bodyToFlux(AuthorDto.class)
                .collectList()
                .block();
    }

    public AuthorDto getAuthorById(String id)
    {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/authors/{id}").build(id))
                .retrieve()
                .bodyToMono(AuthorDto.class)
                .block();
    }
}
