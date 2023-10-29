package com.carrot.easy.domain;

import com.carrot.easy.controller.UploadFile;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Item {


    @Id
    @GeneratedValue
    private Long id;

    private String itemName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Member seller;

    @ManyToOne
    @JoinColumn(name="buyer_id")
    private Member member;

    private int price;

    private String content;

    private int interestCount = 0; //관심 수

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    private UploadFile image;

    @Embedded
    private Address address;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    public void addInterestCount(){
        this.interestCount ++;
    }

    public void minusInterestCount(){
        this.interestCount --;
    }


    public Item() {
    }

    public Item(String itemName, Member seller, int price, String content, int interestCount, Address address, LocalDateTime createDate, UploadFile image) {
        this.itemName = itemName;
        this.seller = seller;
        this.price = price;
        this.content = content;
        this.interestCount = interestCount;
        this.address = address;
        this.createDate = createDate;
        this.image = image;
    }

}
