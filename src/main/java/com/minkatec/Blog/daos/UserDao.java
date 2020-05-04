package com.minkatec.Blog.daos;

import com.minkatec.Blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndActive(String username, boolean active);

}
