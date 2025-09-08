package com.adminapp.admin.frontend.views.lists;

import com.adminapp.admin.frontend.layout.MainLayout;
import com.adminapp.admin.models.Users;
import com.adminapp.admin.services.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@Route(value = "/users", layout = MainLayout.class)
@PermitAll
public class UsersView extends VerticalLayout {
    private final UserService userService;
    private final Grid<Users> grid;

    public UsersView(UserService userService) {
        this.userService = userService;
        this.grid = new Grid<>(Users.class);

        setSizeFull();
        setPadding(true);
        setSpacing(true);

        H2 title = new H2("All Registered Users");
        add(title);

        configureGrid();
        add(grid);
    }

    private void configureGrid() {
        grid.setSizeFull();
        grid.setItems(userService.getAllUsers());

        // Remove auto-generated columns
        grid.removeAllColumns();

        // Add only the columns you want
        grid.addColumn(Users::getUserId).setHeader("ID").setAutoWidth(true);
        grid.addColumn(Users::getPhone).setHeader("Name").setAutoWidth(true);
        grid.addColumn(Users::getHasDisability).setHeader("Email").setAutoWidth(true);
    }
}
