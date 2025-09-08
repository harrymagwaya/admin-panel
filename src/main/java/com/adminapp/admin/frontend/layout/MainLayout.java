package com.adminapp.admin.frontend.layout;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class MainLayout extends AppLayout {
      public MainLayout() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("Woord en Daad connects App");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        SideNav nav = getSideNav();

        Scroller scroller = new Scroller(nav);
        scroller.setClassName(LumoUtility.Padding.SMALL);

        addToDrawer(scroller);
        addToNavbar(toggle, title);
    }

    private SideNav getSideNav() {
        SideNav sideNav = new SideNav();
        sideNav.addItem(
                new SideNavItem("Dashboard", "/dashboard",
                        VaadinIcon.DASHBOARD.create()),
                new SideNavItem("Users", "/users", VaadinIcon.USER_CARD.create()),
                new SideNavItem("Loans", "/loans",
                        VaadinIcon.INVOICE.create()),
                new SideNavItem("Service Providers", "/providers",
                        VaadinIcon.BRIEFCASE.create()),
                new SideNavItem("Profile", "/profile", VaadinIcon.USER.create()));
          
        return sideNav;
    }
}
