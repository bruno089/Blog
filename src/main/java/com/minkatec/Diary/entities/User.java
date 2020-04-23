package com.minkatec.Diary.entities;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity(name = "User")
@javax.persistence.Table(name = "\"User\"")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Role[] getRoles() {
        return roles;
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
    }


}
