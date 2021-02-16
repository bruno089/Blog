package com.minkatec.Blog.api_rest_controllers;

import com.minkatec.Blog.business_controllers.BlogController;
import com.minkatec.Blog.dtos.ArticleDto;
import com.minkatec.Blog.dtos.BlogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



@PreAuthorize("hasRole('ADMIN') or hasRole('WRITER') or hasRole('AUTHENTICATED')")
@RestController
@RequestMapping(BlogResource.BLOGS)
public class BlogResource {
    public static final String BLOGS = "/blogs";
    public static final String BLOG_ID = "/{idBlog}";
    public static final String ARTICLES = "/articles";

    @Autowired
    BlogController blogController;

    @PostMapping
    public BlogDto create( @RequestBody BlogDto blogDto){
        return blogController.create(blogDto);
    }

    //TODO LIST BY USER

    //TODO GET BY ID BLOG

    //TODO LIST BY USER

    //TODO ELIMINAR  BLOG SOLO EL USUARIO CREADO????
    //TODO MODIFICAR BLOG SOLO EL USUARIO CREADO????

    @PostMapping( BLOG_ID + ARTICLES )
    public ResponseEntity createArticle(@PathVariable int idBlog, @RequestBody ArticleDto articleDto){
        return ResponseEntity.ok(blogController.createArticle(idBlog,articleDto));
    }
}
