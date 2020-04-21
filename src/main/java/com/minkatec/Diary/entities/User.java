package com.minkatec.Diary.entities;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity(name = "User")
@javax.persistence.Table(name = "\"User\"")
public class User {
    @Id
    int id;
    String name;
    @Column( unique = true, nullable = false)
    String nick;
    @Column(unique = true, nullable = false)
    String email;
    String password;
    boolean active;
    LocalDateTime registrationDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    Role role;

}
