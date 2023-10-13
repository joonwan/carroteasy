package com.carrot.easy.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
public class Address {

    private String city;
    private String gu;
    private String dong;

    public Address() {
    }
}
