package com.minkatec.Blog.business_controllers;

import com.minkatec.Blog.daos.BlogDao;
import com.minkatec.Blog.dtos.ArticleDto;
import com.minkatec.Blog.dtos.BlogDto;
import com.minkatec.Blog.entities.Article;
import com.minkatec.Blog.entities.Blog;
import com.minkatec.Blog.entities.User;
import com.minkatec.Blog.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BlogController {
    @Autowired    BlogDao blogDao;

    @Autowired    UserController userController;

    public BlogDto create(BlogDto blogDto){

        User user =  userController.getCurrentUser();

        Blog newBlog = Blog.builder()
                .title(blogDto.getTitle())
                .registrationDate(LocalDateTime.now())
                .user(user)
                .build();

        return new BlogDto(blogDao.save(newBlog));
    }

    public Article createArticle(int  idBlog, ArticleDto articleDto) {

        Blog blog = blogDao.findByIdAndUser(idBlog,userController.getCurrentUser())
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

    public List<Article> readAllArticles(int idBlog){
       Blog blog = blogDao
               .findByIdAndUser(idBlog,this.userController.getCurrentUser())
               .orElseThrow(()-> new NotFoundException("Blog: " + idBlog +" not found."));
        return blog.getArticles();
    }
}
