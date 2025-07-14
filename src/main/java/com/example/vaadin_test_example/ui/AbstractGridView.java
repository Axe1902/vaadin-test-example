package com.example.vaadin_test_example.ui;

import com.example.vaadin_test_example.client.ClientService;
import com.example.vaadin_test_example.client.dto.AuthorDto;
import com.example.vaadin_test_example.util.RestResponsePage;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.List;

public abstract class AbstractGridView<T> extends VerticalLayout
{
    protected final TextField search = new TextField("Поиск");
    protected final Grid<T> grid;
    protected final Button prevButton = new Button("Назад");
    protected final Button nextButton = new Button("Вперёд");
    protected final Span pageInfo = new Span();

    protected final ClientService clientService;

    protected int pageNumber = 0;
    protected final int pageSize = 10;

    // забито гвоздями,
    // т.к. лень писать в беке метод подсчета количества записей
    private final static int totalItems = 6;

    public AbstractGridView(Class<T> dto, ClientService clientService)
    {
        this.grid = new Grid<>(dto);
        this.clientService = clientService;

        initLayout();
        initListeners();
        updateGrid();
    }

    private void initLayout()
    {
        grid.setColumns(getCustomGridColumns());

        HorizontalLayout paginationControls = new HorizontalLayout(prevButton, nextButton, pageInfo);

        add(search, grid, paginationControls);
    }

    private void initListeners()
    {
        search.addValueChangeListener(e -> {
            grid.setItems(fetchItems(0, pageSize, search.getValue()));
            pageNumber = 0;
            updatePageInfo();
        });

        grid.addItemClickListener(event -> {
            onItemClick(event.getItem());
        });

        prevButton.addClickListener(e -> {
            if (pageNumber > 0) {
                pageNumber--;
                updateGrid();
            }
        });

        nextButton.addClickListener(e -> {
            if ((pageNumber + 1) * pageSize < totalItems) {
                pageNumber++;
                updateGrid();
            }
        });
    }

    private void updateGrid()
    {
        var items = fetchItems(pageNumber, pageSize, search.getValue());
        grid.setItems(items);

        updatePageInfo();
    }

    private void updatePageInfo()
    {
        int from = pageNumber * pageSize + 1;
        int to = Math.min((pageNumber + 1) * pageSize, totalItems);
        pageInfo.setText("Показаны записи с " + from + " по " + to + " из " + totalItems);

        prevButton.setEnabled(pageNumber > 0);
        nextButton.setEnabled((pageNumber + 1) * pageSize < totalItems);
    }

    protected abstract String[] getCustomGridColumns();

    protected abstract List<T> fetchItems(int pageNumber, int pageSize, String search);

    protected abstract void onItemClick(T item);
}
