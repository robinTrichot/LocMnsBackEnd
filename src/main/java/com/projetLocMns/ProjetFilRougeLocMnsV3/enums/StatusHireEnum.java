package com.projetLocMns.ProjetFilRougeLocMnsV3.enums;

import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public enum StatusHireEnum {
    PENDING("en attente"),
    ON_GOING("en cours"),
    TERMINE("termine");
    private String value;
    StatusHireEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
