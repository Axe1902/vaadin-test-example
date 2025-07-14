package com.example.vaadin_test_example.util;

import com.fasterxml.jackson.annotation.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@JsonIgnoreProperties({"pageable"})
public class RestResponsePage<T> extends PageImpl<T> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RestResponsePage(@JsonProperty("content") List<T> content,
                            @JsonProperty("totalElements") long totalElements,
                            @JsonProperty("number") int number,
                            @JsonProperty("size") int size) {
        super(content, PageRequest.of(number, size), totalElements);
    }
}
