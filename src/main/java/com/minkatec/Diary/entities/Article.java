package com.minkatec.Diary.entities;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Article {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false, length = 50)
    String title;
    @Lob
    String content;
    @Column(nullable = true, length = 500)
    String summary;
    boolean visible;
    LocalDateTime loadDate;
    @OneToMany( cascade = CascadeType.ALL)
    List<Comment> comments;
    @ManyToMany    @OrderColumn()
    Tag[] tags;

}
