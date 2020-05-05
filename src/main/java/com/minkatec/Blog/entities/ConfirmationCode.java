package com.minkatec.Blog.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class ConfirmationCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(unique = true)
    private String code;
    private LocalDateTime expiredDataToken;
    private LocalDateTime createdDate;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;


    public  ConfirmationCode(User user){
        this.user = user;
        this.code = "S3CR3T";
        this.createdDate = LocalDateTime.now();
        this.expiredDataToken =  LocalDateTime.now().plusHours(1);
    }
}
