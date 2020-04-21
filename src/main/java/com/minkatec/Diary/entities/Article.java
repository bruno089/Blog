package com.minkatec.Diary.entities;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Article {
    @Id
    int id;
    String title;
    String content;
    String summary;
    boolean visible;
    LocalDateTime loadDate;

    @OneToMany( cascade = CascadeType.ALL)
    List<Comment> comments;
    @ManyToMany    @OrderColumn()
    Tag[] tags;

}
