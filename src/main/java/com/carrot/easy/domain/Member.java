package com.carrot.easy.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.List;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Double mannerTemperature;

    @Embedded
    private Address address;

    public Member(String name, Double mannerTemperature, Address address) {
        this.name = name;
        this.mannerTemperature = mannerTemperature;
        this.address = address;
    }

    public Member() {
    }
}
