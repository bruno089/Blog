package com.minkatec.Blog.entities;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false, length = 1000)
    String text;
    LocalDateTime loadDate;
    @JoinColumn    @ManyToOne
    User user;


}
