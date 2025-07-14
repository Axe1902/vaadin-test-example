package com.example.vaadin_test_example.ui;

import com.example.vaadin_test_example.client.ClientService;
import com.example.vaadin_test_example.client.dto.BookDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "main", layout = MainLayout.class)
public class MainView extends VerticalLayout
{
    public MainView(ClientService clientService)
    {
        List<BookDto> books = clientService.getLast10Books();

        Grid<BookDto> grid = new Grid<>(BookDto.class);
        grid.setItems(books);
        grid.setColumns("title", "description", "authorId");
        add(new H2("Последние 10 добавленных книг"), grid);
    }
}
