package com.carrot.easy.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class LifePost {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private LifePostSubject subject;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    private LocalDateTime createDateTime;

    private int likeCount;

    public LifePost() {
    }


    public LifePost(String title, String content, Member writer, LocalDateTime createDateTime, int likeCount) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createDateTime = createDateTime;
        this.likeCount = likeCount;
    }
}
