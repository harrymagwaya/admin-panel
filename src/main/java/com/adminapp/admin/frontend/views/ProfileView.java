package com.adminapp.admin.frontend.views;

import com.adminapp.admin.frontend.layout.MainLayout;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import jakarta.annotation.security.PermitAll;

@Route(value = "/profile", layout = MainLayout.class)
@PermitAll
public class ProfileView extends VerticalLayout {
    ProfileView(){

       FormLayout names = new FormLayout();
       TextField naField = new TextField("Name");
       TextField emailField = new TextField("Email");
       names.add(naField, 2);
       names.add(emailField, 2);
       
       FormLayout location = new FormLayout();
       TextField locationField = new TextField("Location ");
       location.add(locationField, 2);

        Details detailsName = new Details("Names and Contact information", names);
        Details details = new Details("Location of organisation", location);
       

        detailsName.setOpened(true);
        details.setOpened(true);
    
        
       add(detailsName, details);
       // setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setAlignItems(Alignment.CENTER);
    }
}
