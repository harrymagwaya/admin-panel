package com.adminapp.admin.frontend.views;

import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;

@Route("/login")
public class LoginView  extends LoginOverlay implements AfterNavigationObserver {
    public LoginView() {
        // Demo purposes only
        // getStyle().set("background-color", "var(--lumo-contrast-5pct)")
        //         .set("display", "flex").set("justify-content", "center")
        //         .set("padding", "var(--lumo-space-l)");

        // LoginForm loginForm = new LoginForm();

        // loginForm.getElement().setAttribute("no-autofocus", "");
        // loginForm.setAction("login");
        // loginForm.setError(true);
        // add(loginForm);
        // Prevent the example from stealing focus when browsing the
        // documentation
        setAction("login");
        // setOpened(true);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAuthenticated = auth != null &&
                                  auth.isAuthenticated() &&
                                  !(auth instanceof AnonymousAuthenticationToken);

        if (isAuthenticated) {
            setOpened(false); 
            UI.getCurrent().access(() -> UI.getCurrent().navigate("dashboard"));
        }else {
            setOpened(true); 
        }

        // Optional: show error if login failed
        String errorParam = VaadinService.getCurrentRequest().getParameter("error");
        if (errorParam != null) {
            setError(true);
        }
}
}

