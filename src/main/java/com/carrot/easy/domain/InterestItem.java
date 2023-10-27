package com.carrot.easy.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class InterestItem {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public InterestItem() {
    }

    public InterestItem(Member member, Item item) {
        this.member = member;
        this.item = item;
    }

    public void addThisToMember(Member member){
        this.member = member;
        member.getInterestItems().add(this);
    }
}
