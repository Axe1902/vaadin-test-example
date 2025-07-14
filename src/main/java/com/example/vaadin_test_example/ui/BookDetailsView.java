package com.example.vaadin_test_example.ui;

import com.example.vaadin_test_example.client.ClientService;
import com.example.vaadin_test_example.client.dto.AuthorDto;
import com.example.vaadin_test_example.client.dto.BookDto;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.*;

@Route(value = "book", layout = MainLayout.class)
public class BookDetailsView extends VerticalLayout implements HasUrlParameter<Long>
{
    private final ClientService clientService;

    public BookDetailsView(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter Long bookId) {
        BookDto book = clientService.getBooksById(bookId.toString());

        add(new H2(book.title()));

        var textArea = new TextArea();
        textArea.setWidth("100%");
        textArea.setHeight("100%");
        textArea.setValue(book.description());

        add(textArea);

        HorizontalLayout authors = new HorizontalLayout();
        var author = clientService.getAuthorById(book.authorId().toString());

        RouterLink link = new RouterLink(author.firstName(), AuthorDetailsView.class, author.id().longValue());
        authors.add(link);

        add(authors);
    }
}
