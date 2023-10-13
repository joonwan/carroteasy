package com.carrot.easy.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Item {


    @Id
    @GeneratedValue
    private Long id;

    private String iteName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member seller;

    private int Price;

    private int interestCount; //관심 수

    @Embedded
    private Address address;

    private LocalDateTime createDate;

    public Item() {
    }

    public Item(String iteName, Member seller, int price, int interestCount, Address address, LocalDateTime createDate) {
        this.iteName = iteName;
        this.seller = seller;
        Price = price;
        this.interestCount = interestCount;
        this.address = address;
        this.createDate = createDate;
    }
}
