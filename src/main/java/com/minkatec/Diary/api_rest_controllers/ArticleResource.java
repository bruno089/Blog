package com.minkatec.Diary.api_rest_controllers;

import com.minkatec.Diary.business_controllers.ArticleController;
import com.minkatec.Diary.dtos.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController()
@RequestMapping(ArticleResource.ARTICLES)
public class ArticleResource {
    public static final String ARTICLES = "/articles";
    @Autowired    ArticleController articleController;

    @PreAuthorize("authenticated")
    @PostMapping()
    public void create(@Valid @RequestBody ArticleDto articleDto){
    }

    @PreAuthorize("authenticated")
    @PutMapping
    public void update(@Valid @RequestBody ArticleDto articleDto){
    }





}
