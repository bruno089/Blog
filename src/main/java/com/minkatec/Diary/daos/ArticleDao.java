package com.minkatec.Diary.daos;

import com.minkatec.Diary.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDao  extends JpaRepository<Article,Integer> {

}
