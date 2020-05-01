package com.minkatec.Blog.business_controllers;

import com.minkatec.Blog.business_services.JwtService;
import com.minkatec.Blog.daos.BlogDao;
import com.minkatec.Blog.daos.UserDao;
import com.minkatec.Blog.dtos.ArticleDto;
import com.minkatec.Blog.dtos.BlogDto;
import com.minkatec.Blog.entities.Article;
import com.minkatec.Blog.entities.Blog;
import com.minkatec.Blog.entities.User;
import com.minkatec.Blog.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class BlogController {
    @Autowired
    BlogDao blogDao;
    @Autowired    JwtService jwtService;
    @Autowired    UserDao userDao;
    @Autowired    UserController userController;

    public BlogDto create(BlogDto blogDto){
        //Todo Buscar el usuario desde el token
        //TODO poner ese metodo en usuario controller, o en un servicio

        User user = userDao.findByUsername("blopez")
                .orElseThrow(()-> new NotFoundException("Not found User"));

        Blog newBlog = Blog.builder()
                .title(blogDto.getTitle())
                .registrationDate(LocalDateTime.now())
                .user(user)
                .build();

        return new BlogDto(blogDao.save(newBlog));
    }

    public void createArticle(int  idBlog, ArticleDto articleDto) {

        Blog blog =  blogDao.findById(idBlog)
                .orElseThrow(()-> new NotFoundException("Blog not found."));

        Article newArticle =  Article.builder()
                .content(articleDto.getContent())
                .loadDate(LocalDateTime.now())
                .summary(articleDto.getSummary())
                .title(articleDto.getTitle())
                .visible(true)
                .build();

        blog.addArticle(newArticle);

        blogDao.save(blog);
    }

}
