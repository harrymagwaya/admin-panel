package com.adminapp.admin.frontend.views.lists;

import com.adminapp.admin.frontend.layout.MainLayout;
import com.adminapp.admin.models.LoanApplications;
import com.adminapp.admin.models.ServiceProviders;
import com.adminapp.admin.services.AdminApplicationService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@Route(value = "/providers", layout = MainLayout.class)
@PermitAll
public class ProvidersView extends VerticalLayout{
    private final AdminApplicationService userService;
    private final Grid<ServiceProviders> grid;

     private ServiceProviders expandedItem = null;

    public ProvidersView(AdminApplicationService userService) {
        this.userService = userService;
        this.grid = new Grid<>(ServiceProviders.class);

        setSizeFull();
        setPadding(true);
        setSpacing(true);

        H2 title = new H2("Service Provider Verification Dashboard");
        add(title);

        configureGrid();
        add(grid);
    }

    private void configureGrid() {
        grid.setItems(userService.getAllUsers());
        grid.setSizeFull();

        // Remove auto-generated columns
        grid.removeAllColumns();

        // Display selected columns only
        grid.addColumn(ServiceProviders::getProviderId).setHeader("ID").setAutoWidth(true);
        grid.addColumn(ServiceProviders::getUserName).setHeader("Name").setAutoWidth(true);
        grid.addColumn(ServiceProviders::getEmail).setHeader("Email").setAutoWidth(true);
        grid.addColumn(ServiceProviders::getAreaOfExpertise).setHeader("Email").setAutoWidth(true);
        grid.addColumn(ServiceProviders::getLocation).setHeader("Email").setAutoWidth(true);
        grid.addColumn(user -> user.getIsEnabled() ? "✅ Verified" : "❌ Pending")
            .setHeader("Status").setAutoWidth(true);

        // Details renderer with confirmation dialog
        grid.setItemDetailsRenderer(new ComponentRenderer<>(user -> {
            VerticalLayout detailsLayout = new VerticalLayout();
            detailsLayout.setPadding(true);
            detailsLayout.setSpacing(true);
            detailsLayout.getStyle().set("background-color", "#f3f3f3");
            detailsLayout.getStyle().set("border", "1px solid #ddd");
            detailsLayout.getStyle().set("border-radius", "8px");
            detailsLayout.setWidthFull();

            detailsLayout.add(new H4("Details for " + user.getUserName()));
            detailsLayout.add(new Span("Email: " + user.getEmail()));
            detailsLayout.add(new Span("Area of Expertise: " + user.getAreaOfExpertise()));
            detailsLayout.add(new Span("Verified: " + (user.getIsEnabled() ? "Yes" : "No")));

            Button approveBtn = new Button("Approve");
            approveBtn.setEnabled(user.getIsEnabled());
            approveBtn.addClickListener(e -> {
                ConfirmDialog dialog = new ConfirmDialog();
                dialog.setHeader("Confirm Approval");
                dialog.setText("Are you sure you want to approve " + user.getEmail() + "?");

                dialog.setConfirmText("Approve");
                dialog.setCancelText("Cancel");

                dialog.addConfirmListener(confirmEvent -> {
                    userService.approve(user.getProviderId());
                    grid.getDataProvider().refreshItem(user);
                    Notification.show("User approved", 3000, Notification.Position.MIDDLE);
                });

                dialog.open();
                
            });
            

            Button ignoreBtn = new Button("Ignore", e -> {
                Notification.show("User ignored", 3000, Notification.Position.MIDDLE);
            });

            HorizontalLayout actions = new HorizontalLayout(approveBtn, ignoreBtn);
            detailsLayout.add(actions);

            return detailsLayout;
        }));

        // Toggle details on row click
        grid.addItemClickListener(event -> {
            ServiceProviders clickedUser = event.getItem();
           // grid.setDetailsVisible(clickedUser, !grid.isDetailsVisible(clickedUser));

            if (clickedUser.equals(expandedItem)) {
                grid.setDetailsVisible(clickedUser, false);
                expandedItem = null;
            } else {
                if (expandedItem != null) {
                    grid.setDetailsVisible(expandedItem, false);
                }
                grid.setDetailsVisible(clickedUser, true);
                expandedItem = clickedUser;
            }
        });
    }
}
