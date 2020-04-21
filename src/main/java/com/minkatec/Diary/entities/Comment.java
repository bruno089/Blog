package com.minkatec.Diary.entities;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    int id;
    String text;
    LocalDateTime loadDate;
    @JoinColumn    @ManyToOne
    User user;


}
