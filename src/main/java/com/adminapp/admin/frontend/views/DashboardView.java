package com.adminapp.admin.frontend.views;


import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.adminapp.admin.frontend.layout.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;


@Route(value = "/dashboard", layout = MainLayout.class)
@PermitAll
public class DashboardView extends VerticalLayout implements BeforeEnterObserver {

   
    DashboardView(){
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        H2 title = new H2("Loan Application Metrics");
        add(title);

        // Create a layout to hold the cards
        Div cardContainer = new Div();
        cardContainer.getStyle().set("display", "grid");
        cardContainer.getStyle().set("grid-template-columns", "repeat(3, 1fr)");
        cardContainer.getStyle().set("gap", "20px");
        cardContainer.setWidthFull();
        // Add cards
        cardContainer.add(createCard("Approved Loans", 128));
        cardContainer.add(createCard("Pending Applications", 42));
        cardContainer.add(createCard("Rejected Loans", 17));
        cardContainer.add(createCard("Total Providers", 300));
        cardContainer.add(createCard("Active Loans", 89));
        cardContainer.add(createCard("Disbursed Today", 12));

        add(cardContainer, popularArea());
    }

    private Component createCard(String title, int number) {
        VerticalLayout card = new VerticalLayout();
        card.setPadding(true);
        card.setSpacing(false);
        card.setAlignItems(Alignment.CENTER);
        card.getStyle()
            .set("border", "1px solid #ddd")
            .set("border-radius", "8px")
            .set("background-color", "#f9f9f9")
            .set("box-shadow", "0 2px 4px rgba(0,0,0,0.1)")
            .set("padding", "20px")
            .set("text-align", "center");

        Span titleSpan = new Span(title);
        titleSpan.getStyle().set("font-weight", "600").set("font-size", "16px");

        Span numberSpan = new Span(String.valueOf(number));
        numberSpan.getStyle().set("font-size", "32px").set("color", "#007bff");

        card.add(titleSpan, numberSpan);
        return card;
    }

    public Component popularArea(){
        VerticalLayout card = new VerticalLayout();
        card.setWidthFull();
        card.setPadding(true);
        card.setSpacing(true);
        card.setAlignItems(Alignment.CENTER);
        card.getStyle().set("border", "1px solid #ddd")
                .set("border-radius", "12px")
                .set("box-shadow", "0 4px 8px rgba(0,0,0,0.1)")
                .set("background-color", "#ffffff")
                .set("padding", "20px");

        H2 title = new H2("Popular Areas");
        title.getStyle().set("margin-bottom", "20px");

        // Map Image
        Image mapImage = new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSGH0wffhT3chIWyD299yul0k6bYeSd1llBOg&s", "Map of Popular Areas");
        mapImage.setWidth("100%");
        mapImage.setHeight("400px");
        mapImage.getStyle().set("object-fit", "cover")
                           .set("border-radius", "8px");

        card.add(title, mapImage);
        return card;
    }
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    boolean isAuthenticated = authentication != null &&
                              authentication.isAuthenticated() &&
                              !(authentication instanceof AnonymousAuthenticationToken);

    if (!isAuthenticated) {
        event.forwardTo("login"); 
    }
    }

    
    
}
