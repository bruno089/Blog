package com.minkatec.Diary.data_services;

import com.minkatec.Diary.daos.UserDao;
import com.minkatec.Diary.dtos.UserDto;
import com.minkatec.Diary.entities.Role;
import com.minkatec.Diary.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class AuthService {
    @Autowired
    private UserDao userDao;



    public void signup(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        //user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setActive(true);
        user.setRegistrationDate( LocalDateTime.now());
        user.setRoles( new Role[]{Role.ADMIN,Role.WRITER});

        userDao.save(user);


    }
}
