package com.minkatec.Diary.business_controllers;

import com.minkatec.Diary.daos.ArticleDao;
import com.minkatec.Diary.dtos.ArticleDto;
import com.minkatec.Diary.entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class ArticleController {
    @Autowired    ArticleDao articleDao;

    public Article create(ArticleDto articleDto){
        return null;
    }
}
