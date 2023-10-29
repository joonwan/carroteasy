package com.carrot.easy.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String loginId;

    private String password;

    private String name;

    private Double mannerTemperature;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<InterestItem> interestItems = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<InterestPost> interestPosts = new ArrayList<>();


    public Member(String loginId, String password, String name, Double mannerTemperature, Address address) {

        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.mannerTemperature = mannerTemperature;
        this.address = address;
    }

    public Member() {
    }

}
