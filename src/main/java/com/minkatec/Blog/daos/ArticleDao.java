package com.minkatec.Blog.daos;

import com.minkatec.Blog.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDao  extends JpaRepository<Article,Integer> {

}
