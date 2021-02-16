package com.minkatec.Blog.api_rest_controllers;

import com.minkatec.Blog.business_controllers.UserController;
import com.minkatec.Blog.dtos.TokenOutputDto;
import com.minkatec.Blog.dtos.UserDto;
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
    public static final String CONFIRM = "/confirm";

    @Autowired
    UserController userController;

    @PostMapping(REGISTER)
    public void register(@RequestBody UserDto userDto){
        userController.register(userDto);
    }

    @GetMapping(CONFIRM) //TODO Averiguar que devuelve este tipo de peticion tradicionalmente
    public void confirmUserAccount(@RequestParam() String confirmationCode){
        userController.confirmUserAccount(confirmationCode);
    }

    @PreAuthorize("authenticated")
    @PostMapping(LOGIN)
    public TokenOutputDto login(@AuthenticationPrincipal User activeUser) {
        return userController.login(activeUser.getUsername());
    }

}
