package com.minkatec.Diary.api_rest_controllers;

import com.minkatec.Diary.business_controllers.UserController;
import com.minkatec.Diary.dtos.TokenOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserResource.USERS)

public class UserResource {
    public static final String USERS = "/users";
    public static final String TOKEN = "/token";

    @Autowired    UserController userController;

    @PreAuthorize("authenticated")
    @PostMapping(value = TOKEN)
    public TokenOutputDto login(@AuthenticationPrincipal User activeUser) {
        return userController.login(activeUser.getUsername());
    }


}
