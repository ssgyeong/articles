package com.my.articles.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter //@Data로 쓰면 스택 오버플로우 나서 getter와 setter로 작성
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;
}