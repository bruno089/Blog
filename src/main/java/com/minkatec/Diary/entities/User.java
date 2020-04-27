package com.minkatec.Diary.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "User")
@javax.persistence.Table(name = "\"User\"")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column( nullable = false)
    String name;
    @Column( unique = true, nullable = false)
    String username;
    @Column(unique = true, nullable = false)
    String email;
    String password;
    boolean active;
    LocalDateTime registrationDate;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER) @OrderColumn
    Role[] roles;


}
