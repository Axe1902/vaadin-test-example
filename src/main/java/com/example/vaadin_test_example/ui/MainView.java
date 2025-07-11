package com.example.vaadin_test_example.ui;

import com.example.vaadin_test_example.client.ClientService;
import com.example.vaadin_test_example.client.dto.BookDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    public MainView(ClientService clientService) {

        TextField idField = new TextField("Введите ID");
        idField.setPlaceholder("ID");  // подсказка внутри поля
        idField.setWidth("200px");
        add(idField);

        Button loadBooksButton = new Button("Load Books", event -> {

            String id = idField.getValue();

            BookDto book = clientService.getBooksById(id);
            // Отобразите книги в UI, например, в Grid
            Grid<BookDto> grid = new Grid<>(BookDto.class);
            grid.setItems(book);
            add(grid);
        });

        add(loadBooksButton);
    }
}
