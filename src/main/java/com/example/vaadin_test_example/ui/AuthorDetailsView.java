package com.example.vaadin_test_example.ui;

import com.example.vaadin_test_example.client.ClientService;
import com.example.vaadin_test_example.client.dto.BookDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "author", layout = MainLayout.class)
public class AuthorDetailsView extends VerticalLayout implements HasUrlParameter<Long>
{
    private final ClientService clientService;

    public AuthorDetailsView(ClientService clientService) {
        this.clientService = clientService;
    }


    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter Long authorId)
    {
        List<BookDto> books = clientService.getBooksByAuthor(authorId.intValue());

        Grid<BookDto> grid = new Grid<>(BookDto.class);
        grid.setItems(books);
        grid.addItemClickListener(ev -> {
            BookDto book = ev.getItem();
            UI.getCurrent().navigate(BookDetailsView.class, book.id().longValue());
        });
        add(new H2("Книги автора"), grid);
    }
}
