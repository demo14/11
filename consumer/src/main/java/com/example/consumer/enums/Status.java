package com.example.consumer.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status implements StatusAsString{
    ACTIVE("Активный"),
    BLOCKED("Заблокирован");

    ;

    private final String localName;



    @Override
    public String getLocalName() {
        return localName;
    }
}
