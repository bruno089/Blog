package com.minkatec.Blog.daos;

import com.minkatec.Blog.entities.Blog;
import com.minkatec.Blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BlogDao extends JpaRepository<Blog,Integer> {

    Optional<Blog> findByIdAndUser(int idBlog, User user);

}
