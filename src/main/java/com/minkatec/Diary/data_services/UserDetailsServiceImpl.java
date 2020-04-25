package com.minkatec.Diary.data_services;

import com.minkatec.Diary.daos.UserDao;
import com.minkatec.Diary.dtos.UserDto;
import com.minkatec.Diary.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    public static final String TEST_PASSWORD = "123456";
    @Autowired UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
         com.minkatec.Diary.entities.User user = userDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found. " + username));

        return this.userBuilder
                (user.getUsername(), user.getPassword(), new Role[]{Role.AUTHENTICATED}, user.isActive());
      /*  if("admin".equals(username)){
            return this.userBuilder("admin",new BCryptPasswordEncoder().encode(TEST_PASSWORD),
                      new Role[]{Role.ADMIN},true);
        }else if("writer".equals(username)){
            return this.userBuilder("writer",new BCryptPasswordEncoder().encode(TEST_PASSWORD),
                    new Role[]{Role.WRITER},true);

        }else if("guest".equals(username)){
            return this.userBuilder("guest",new BCryptPasswordEncoder().encode(TEST_PASSWORD),
                    new Role[]{Role.GUEST},true);
        }
        else {
            throw  new UsernameNotFoundException("User not found");
        }*/
    }

    private org.springframework.security.core.userdetails.User userBuilder(String username, String password, Role[] roles,boolean active) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.roleName()));
        }
        return  new User(username, password, active,
                true,true, true, authorities);
    }





}
