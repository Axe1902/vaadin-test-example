package com.example.vaadin_test_example.ui;

import com.example.vaadin_test_example.client.ClientService;
import com.example.vaadin_test_example.client.dto.BookDto;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "books", layout = MainLayout.class)
public class BooksView extends AbstractGridView<BookDto>
{
    private final static String[] columns = new String[] {"title", "description"};

    public BooksView(ClientService clientService)
    {
        super(BookDto.class, clientService);
    }

    @Override
    protected String[] getCustomGridColumns() {
        return columns;
    }

    @Override
    protected List<BookDto> fetchItems(int pageNumber, int pageSize, String search) {
        if (search == null || search.isEmpty()) {
            return clientService.getAllBooks(pageNumber, pageSize).getContent();
        }
        else
        {
            return clientService.getBookByTitle(search);
        }
    }

    @Override
    protected void onItemClick(BookDto item) {
        UI.getCurrent().navigate(BookDetailsView.class, item.id().longValue());
    }
}
