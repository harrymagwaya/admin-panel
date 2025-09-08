package com.adminapp.admin.frontend.views.lists;

import com.adminapp.admin.frontend.layout.MainLayout;
import com.adminapp.admin.models.LoanApplications;
import com.adminapp.admin.models.ServiceProviders;
import com.adminapp.admin.models.LoanApplications.StatusOfApp;
import com.adminapp.admin.services.AdminApplicationService;
import com.adminapp.admin.services.AdminLoanAppService;
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

@Route(value = "/loans", layout = MainLayout.class)
@PermitAll
public class LoansView extends VerticalLayout{
    private final AdminLoanAppService userService;
    private final Grid<LoanApplications> grid;
    private LoanApplications expandedItem = null;

    public LoansView(AdminLoanAppService userService) {
        this.userService = userService;
        this.grid = new Grid<>(LoanApplications.class);

        setSizeFull();
        setPadding(true);
        setSpacing(true);

        H2 title = new H2("Loans Approval Dashboard");
        add(title);

        configureGrid();
        add(grid);
    }

    private void configureGrid() {
        grid.setItems(userService.getAllLoans());
        grid.setSizeFull();

        
        // Remove auto-generated columns
        grid.removeAllColumns();

        // Display selected columns only
        grid.addColumn(LoanApplications::getId).setHeader("ID").setAutoWidth(true);
        grid.addColumn(LoanApplications::getRequesterId).setHeader("Name").setAutoWidth(true);
        grid.addColumn(LoanApplications::getReason).setHeader("Reason").setAutoWidth(true);
        grid.addColumn(LoanApplications::getLoanAmount).setHeader("Email").setAutoWidth(true);
        grid.addColumn(LoanApplications::getStatusOfApp).setHeader("Email").setAutoWidth(true);
        grid.addColumn(app -> {
            switch (app.getStatusOfApp()) {
                case APPROVED:
                    return "✅ Approved";
                case REJECTED:
                    return "❌ Rejected";
                case PENDING:
                default:
                    return "⏳ Pending";
            }
        }).setHeader("Status").setAutoWidth(true);

        // Details renderer with confirmation dialog
        grid.setItemDetailsRenderer(new ComponentRenderer<>(user -> {
            VerticalLayout detailsLayout = new VerticalLayout();
            detailsLayout.setPadding(true);
            detailsLayout.setSpacing(true);
            detailsLayout.getStyle().set("background-color", "#f3f3f3");
            detailsLayout.getStyle().set("border", "1px solid #ddd");
            detailsLayout.getStyle().set("border-radius", "8px");
            detailsLayout.setWidthFull();

            detailsLayout.add(new H4("Details for " + user.getReason()));
            detailsLayout.add(new Span("Amount: " + user.getLoanAmount()));
            detailsLayout.add(new Span("Supporting documents " + user.getSuportingDocumentsUrl()));
            
            Button approveBtn = new Button("Approve");
            approveBtn.setEnabled(user.getStatusOfApp() == StatusOfApp.PENDING);
            approveBtn.addClickListener(e -> {
                ConfirmDialog dialog = new ConfirmDialog();
                dialog.setHeader("Confirm Approval");
                dialog.setText("Are you sure you want to approve " + user.getLoanAmount() + "?");

                dialog.setConfirmText("Approve");
                dialog.setCancelText("Cancel");

                dialog.addConfirmListener(confirmEvent -> {
                    userService.approve(user.getId());
                    grid.getDataProvider().refreshItem(user);
                    Notification.show("User approved", 3000, Notification.Position.MIDDLE);
                });

                dialog.open();
                
            });
            

            Button rejectBtn = new Button("Reject");
            rejectBtn.setEnabled(user.getStatusOfApp() == StatusOfApp.PENDING );

            rejectBtn.addClickListener(e -> {
                ConfirmDialog dialog = new ConfirmDialog();
                dialog.setHeader("Confirm Rejection");
                dialog.setText("Are you sure you want to reject this loan application?");

                dialog.setConfirmText("Reject");
                dialog.setCancelText("Cancel");

                dialog.addConfirmListener(confirmEvent -> {
                    userService.reject(user.getId());
                    grid.getDataProvider().refreshItem(user);
                    rejectBtn.setEnabled(false); // Disable after rejection
                    Notification.show("Loan rejected", 3000, Notification.Position.MIDDLE);
                });

                dialog.open();
            });

            HorizontalLayout actions = new HorizontalLayout(approveBtn, rejectBtn);
            detailsLayout.add(actions);

            return detailsLayout;
        }));

       
        // Toggle details on row click
        grid.addItemClickListener(event -> {
            LoanApplications clickedUser = event.getItem();
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
