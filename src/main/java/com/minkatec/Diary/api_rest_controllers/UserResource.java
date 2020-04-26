package com.minkatec.Diary.api_rest_controllers;

import com.minkatec.Diary.business_controllers.UserController;
import com.minkatec.Diary.dtos.TokenOutputDto;
import com.minkatec.Diary.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(UserResource.USERS)

public class UserResource {
    public static final String USERS = "/users";
    public static final String LOGIN = "/login";
    public static final String REGISTER = "/register";
    public static final String USERNAME = "/{username}";

    @Autowired    UserController userController;

    @PostMapping(REGISTER)
    public TokenOutputDto register(@RequestBody UserDto userDto){
        return userController.register(userDto);
    }

    @PreAuthorize("authenticated")
    @PostMapping(value = LOGIN)
    public TokenOutputDto login(@AuthenticationPrincipal User activeUser) {
        return userController.login(activeUser.getUsername());
    }


    @GetMapping(value = USERNAME)
    public UserDto read(@PathVariable String username, @AuthenticationPrincipal User activeUser) {
        return this.userController.readUser(username, SecurityContextHolder.getContext().getAuthentication().getName(),
                SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
    }






}
