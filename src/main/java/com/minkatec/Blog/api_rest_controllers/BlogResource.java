package com.minkatec.Blog.api_rest_controllers;

import com.minkatec.Blog.business_controllers.BlogController;
import com.minkatec.Blog.dtos.ArticleDto;
import com.minkatec.Blog.dtos.BlogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(BlogResource.BLOGS)
public class BlogResource {
    public static final String BLOGS = "/blogs";
    public static final String BLOG_ID = "/{idBlog}";
    public static final String ARTICLES = "/articles";

    @Autowired
    BlogController blogController;

    @PostMapping
    public BlogDto create(@Valid @RequestBody BlogDto blogDto){
        return blogController.create(blogDto);
    }

    @PostMapping( BLOG_ID + ARTICLES )
    public ResponseEntity createArticle(@PathVariable int idBlog, @RequestBody ArticleDto articleDto){
        return ResponseEntity.ok(blogController.createArticle(idBlog,articleDto));
    }
}
