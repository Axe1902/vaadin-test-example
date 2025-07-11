package com.example.vaadin_test_example.client.dto;

public record BookDto (
        Integer id,
        String title,
        String description,
        Integer authorId
){}
