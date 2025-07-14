package com.example.vaadin_test_example.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("")
public class MainLayout extends AppLayout
{
    public MainLayout()
    {
        DrawerToggle toggle = new DrawerToggle();

        VerticalLayout menu = new VerticalLayout(
                new RouterLink("Главная", MainView.class),
                new RouterLink("Авторы", AuthorsView.class),
                new RouterLink("Книги", BooksView.class)
        );

        addToDrawer(menu);
        addToNavbar(toggle, new H1("Книжное приложение"));
    }
}
