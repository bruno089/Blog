package com.minkatec.Blog.business_controllers;

import com.minkatec.Blog.daos.BlogDao;
import com.minkatec.Blog.daos.UserDao;
import com.minkatec.Blog.dtos.ArticleDto;
import com.minkatec.Blog.dtos.BlogDto;
import com.minkatec.Blog.entities.Article;
import com.minkatec.Blog.entities.Blog;
import com.minkatec.Blog.entities.User;
import com.minkatec.Blog.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class BlogController {
    @Autowired    BlogDao blogDao;
    @Autowired    UserDao userDao;
    @Autowired    UserController userController;

    public BlogDto create(BlogDto blogDto){

        User user = userDao
                .findByUsername( userController.getCurrenUsername() )
                .orElseThrow(()-> new NotFoundException("Not found User:" + userController.getCurrenUsername() ));

        Blog newBlog = Blog.builder()
                .title(blogDto.getTitle())
                .registrationDate(LocalDateTime.now())
                .user(user)
                .build();

        return new BlogDto(blogDao.save(newBlog));
    }

    public Article createArticle(int  idBlog, ArticleDto articleDto) {
        // FindByIdAndUser --- Or username in current user
        //Todo puedo en usuario devolver un USER directamente y luego hacerle get a la entidad
            //Todo Then blogDao.findByIdAndUser()
        Blog blog =  blogDao.findById(idBlog)
                .orElseThrow(()-> new NotFoundException("Blog: " + idBlog +" not found."));
        Article newArticle =  Article.builder()
                .content(articleDto.getContent())
                .loadDate(LocalDateTime.now())
                .summary(articleDto.getSummary())
                .title(articleDto.getTitle())
                .visible(true)
                .build();

        blog.addArticle(newArticle);

        blogDao.save(blog);

        return blog.getArticleByTitle(articleDto.getTitle());
    }
}
