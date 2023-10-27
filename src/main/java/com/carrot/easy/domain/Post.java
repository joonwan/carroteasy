package com.carrot.easy.domain;

import jakarta.persistence.*;

@Entity
public class Post {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    private String title;

    private String Content;

    // 조회수
    private int viewCount;

    //공감수
    private int likeCount;

}
