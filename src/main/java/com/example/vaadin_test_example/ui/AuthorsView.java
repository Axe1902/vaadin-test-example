package com.example.vaadin_test_example.ui;

import com.example.vaadin_test_example.client.ClientService;
import com.example.vaadin_test_example.client.dto.AuthorDto;
import com.example.vaadin_test_example.util.RestResponsePage;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "authors", layout = MainLayout.class)
public class AuthorsView extends AbstractGridView<AuthorDto>
{
    private final static String[] columns = new String[] {"firstName", "lastName", "patronymic"};

    public AuthorsView(ClientService clientService)
    {
        super(AuthorDto.class, clientService);
    }

    @Override
    protected String[] getCustomGridColumns() {
        return columns;
    }

    @Override
    protected List<AuthorDto> fetchItems(int pageNumber, int pageSize, String search) {

        if (search == null || search.isEmpty()) {
            return clientService.getAllAuthors(pageNumber, pageSize).getContent();
        }
        else
        {
            return clientService.getAuthorByFirstName(search);
        }
    }

    @Override
    protected void onItemClick(AuthorDto item) {
        UI.getCurrent().navigate(AuthorDetailsView.class, item.id().longValue());
    }
}
