package com.minkatec.Diary.dtos;

import com.minkatec.Diary.entities.User;
import lombok.Data;

@Data
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


}
