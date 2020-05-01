package com.minkatec.Blog.data_services;

import com.minkatec.Blog.daos.UserDao;
import com.minkatec.Blog.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Qualifier("minkatec")
public class UserDetailsServiceImpl  implements UserDetailsService {
    public static final String TEST_PASSWORD = "123456";
    @Autowired UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
         com.minkatec.Blog.entities.User user = userDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found. " + username));
     //Todo posible manejo para no confirmados?
     /*         boolean active = user.isActive();
         if (active == false ){
             throw  new UsernameNotFoundException("username:" + username + " not confirmed yet.");
         }*/

        return this.userBuilder
                (user.getUsername(), user.getPassword(), new Role[]{Role.AUTHENTICATED}, user.isActive());
    }

    private org.springframework.security.core.userdetails.User userBuilder(String username, String password, Role[] roles,boolean active) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.roleName()));
        }
        return  new org.springframework.security.core.userdetails.User(username, password, active,
                true,true, true, authorities);
    }





}
