package com.minkatec.Diary.business_controllers;

import com.minkatec.Diary.business_services.JwtService;
import com.minkatec.Diary.daos.UserDao;
import com.minkatec.Diary.dtos.TokenOutputDto;
import com.minkatec.Diary.dtos.UserDto;
import com.minkatec.Diary.entities.Role;
import com.minkatec.Diary.entities.User;
import com.minkatec.Diary.exceptions.ForbiddenException;
import com.minkatec.Diary.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired    JwtService jwtService;
    @Autowired    UserDao userDao;

    public TokenOutputDto register(UserDto userDto) {
        //Todo Userbuilder - and default password
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setActive(true);
        user.setRegistrationDate( LocalDateTime.now());
        user.setRoles(new Role[]{Role.AUTHENTICATED});
        User userCreated =  userDao.save(user);
        String[] roles = Arrays.stream(userCreated.getRoles()).map(Role::name).toArray(String[]::new);

        return new TokenOutputDto(jwtService.createToken(userCreated.getUsername(), userCreated.getName(),roles));
    }

    public TokenOutputDto login(String username) {
        User user = userDao.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Unexpected!!. Username not found:" + username));
        String[] roles = Arrays.stream(user.getRoles()).map(Role::name).toArray(String[]::new);

        return new TokenOutputDto(jwtService.createToken(user.getUsername(), user.getName(),roles));
    }

    public UserDto readUser(String username, String claimUsername, List<String> claimRoles) {
        User user = this.userDao.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User name:" + username));


        this.authorized(claimUsername, claimRoles, username, Arrays.stream(user.getRoles())
                .map(Role::roleName).collect(Collectors.toList()));
        return new UserDto(user);
    }

    private void authorized(String claimUsername, List<String> claimRoles, String username, List<String> userRoles) {
        if (claimRoles.contains(Role.ADMIN.roleName()) || claimUsername.equals(username)) {
            return;
        }
        if (claimRoles.contains(Role.WRITER.roleName())
                && !userRoles.contains(Role.ADMIN.roleName()) && !userRoles.contains(Role.WRITER.roleName())) {
            return;
        }
        throw new ForbiddenException("User name (" + username + ")");
    }

    public Optional<org.springframework.security.core.userdetails.User> getCurrenUser(){

        return Optional.of((org.springframework.security.core.userdetails.User)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        );
    }
}