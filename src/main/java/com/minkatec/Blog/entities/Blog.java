package com.minkatec.Blog.entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
 import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Blog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(length = 100,unique = true)
    String title;
    @Column(length = 500)
    String description;
    LocalDateTime registrationDate;
    @OneToMany(cascade = CascadeType.ALL)
    List<Article> articles;
    @JoinColumn    @ManyToOne
    User user;

    public void addArticle(Article article){
        this.articles.add(article);
    }


    public Article getArticleByTitle(String title){

        for (Article article: articles){
            if( article.title == title ){
                return article;
            }
        }

        return null;
    }





}
