package com.minkatec.Blog.business_controllers;

import com.minkatec.Blog.daos.ArticleDao;
import com.minkatec.Blog.daos.BlogDao;
import com.minkatec.Blog.dtos.ArticleDto;
import com.minkatec.Blog.entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class ArticleController {
    @Autowired    ArticleDao articleDao;

    @Autowired
    BlogDao blogDao;

    public Article create(ArticleDto articleDto){
        return null;
    }
}
