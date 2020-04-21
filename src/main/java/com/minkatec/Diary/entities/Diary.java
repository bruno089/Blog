package com.minkatec.Diary.entities;
import javax.persistence.*;
 import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Diary {
    @Id
    int id;
    String title;
    LocalDateTime registrationDate;
    @OneToMany
    List<Article> articles;
    @JoinColumn    @ManyToOne
    User user;
}
