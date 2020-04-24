package com.minkatec.Diary.business_controllers;

import com.minkatec.Diary.business_services.JwtService;
import com.minkatec.Diary.daos.UserDao;
import com.minkatec.Diary.dtos.TokenOutputDto;
import com.minkatec.Diary.entities.Role;
import com.minkatec.Diary.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Arrays;

@Controller
public class UserController {

    @Autowired    JwtService jwtService;
    @Autowired    UserDao userDao;



    public TokenOutputDto login(String username) {
        User user = userDao.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Unexpected!!. Username not found:" + username));
        String[] roles = Arrays.stream(user.getRoles()).map(Role::name).toArray(String[]::new);

        return new TokenOutputDto(jwtService.createToken(user.getUsername(), user.getName(),roles));
    }
}