package com.minkatec.Blog.daos;

import com.minkatec.Blog.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogDao extends JpaRepository<Blog,Integer> {
}
