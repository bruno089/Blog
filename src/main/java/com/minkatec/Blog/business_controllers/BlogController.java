package com.minkatec.Blog.business_controllers;

import com.minkatec.Blog.daos.BlogDao;
import com.minkatec.Blog.dtos.ArticleDto;
import com.minkatec.Blog.dtos.BlogDto;
import com.minkatec.Blog.entities.Article;
import com.minkatec.Blog.entities.Blog;
import com.minkatec.Blog.entities.Tag;
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
        //Deberia recibir el user desde el TOKEN
            //Deberia revisar que este usuario no posea ya un blog con ese nombre
        User user =  userController.getCurrentUser();

        Blog newBlog = Blog.builder()
                .title(blogDto.getTitle())
                .description(blogDto.getDescription())
                .registrationDate(LocalDateTime.now())
                .user(user)
                .build();



        return new BlogDto(blogDao.save(newBlog));
    }

    public Article createArticle(int idBlog, ArticleDto articleDto) {
        //TODO Esta funcionando pero tirar errror 500, y no tira el error
        Blog blog = blogDao.findByIdAndUser(idBlog,userController.getCurrentUser())
                .orElseThrow(()-> new NotFoundException("Blog: " + idBlog +" not found for." + userController.getCurrentUser()));

        //Funcionalidad de crear tags

            //Si ya existe tags entonces agregar un contador
            //Sino insertarlo
            //devolver como final un array de tags

        // Dame todos los tags en BD
        // por cada tags que entra fijarme si no existe en bd , si existe usarlo, sino crearlo meterlo en bd
        // y luego devolverselo para el uso
        //Al final me tiene que dar un array de tags todos en base a los que el me paso por parametros
            //return Collection<Tag> tags;




        Article newArticle =  Article.builder()
                .content(articleDto.getContent())
                .loadDate(LocalDateTime.now())
                .summary(articleDto.getSummary())
                .title(articleDto.getTitle())
                .visible(true)
                .chatty(true)
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
