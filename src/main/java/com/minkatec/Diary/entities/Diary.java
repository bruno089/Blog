package com.minkatec.Diary.entities;
import javax.persistence.*;
 import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Diary {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(length = 100)
    String title;
    LocalDateTime registrationDate;
    @OneToMany
    List<Article> articles;
    @JoinColumn    @ManyToOne
    User user;
}
