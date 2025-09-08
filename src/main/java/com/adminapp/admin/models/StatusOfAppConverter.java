package com.adminapp.admin.models;

import com.adminapp.admin.models.LoanApplications.StatusOfApp;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusOfAppConverter implements AttributeConverter<StatusOfApp, String> {

    @Override
    public String convertToDatabaseColumn(StatusOfApp status) {
        return status == null ? null : status.name().toLowerCase(); // store as lowercase
    }

    @Override
    public StatusOfApp convertToEntityAttribute(String dbValue) {
        if (dbValue == null)
            return null;
        try {
            return StatusOfApp.valueOf(dbValue.toUpperCase()); // map to enum
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status value: " + dbValue);
        }
    }
}
