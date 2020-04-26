package com.minkatec.Diary.dtos;

import com.minkatec.Diary.entities.User;

public class UserDto {

    String username;
    String name;
    String password;
    String email;

    public UserDto(){}
    public UserDto(User user) {
        username = user.getUsername();
        password = user.getPassword();
        email = user.getEmail();
        name = user.getName();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
