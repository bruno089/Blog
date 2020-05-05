package com.minkatec.Blog.entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@javax.persistence.Table(name = "\"User\"")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column( nullable = false)
    String name;
    @Column( unique = true, nullable = false, length = 60)
    String username;
    @Column(unique = true, nullable = false, length = 60)
    String email;
    String password;
    boolean active;
    LocalDateTime registrationDate;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER) @OrderColumn
    Role[] roles;


}
