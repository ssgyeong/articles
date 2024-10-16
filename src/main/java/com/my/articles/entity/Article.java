package com.my.articles.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 1000)
    private String content;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    List<Comment> comments = new ArrayList<>();

}
