package com.carrot.easy.domain;

import jakarta.persistence.*;

@Entity
public class InterestPost {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="itemId")
    private Item item;
}
