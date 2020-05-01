package com.minkatec.Blog.entities;
import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tag")
public class Tag {
    @Id
    int id;
    String description;
    LocalDateTime registrationDate;

}
