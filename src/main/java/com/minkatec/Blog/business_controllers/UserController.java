package com.minkatec.Blog.business_controllers;

import com.minkatec.Blog.business_services.JwtService;
import com.minkatec.Blog.business_services.MailService;
import com.minkatec.Blog.daos.ConfirmationCodeDao;
import com.minkatec.Blog.daos.UserDao;
import com.minkatec.Blog.dtos.TokenOutputDto;
import com.minkatec.Blog.dtos.UserDto;
import com.minkatec.Blog.entities.ConfirmationCode;
import com.minkatec.Blog.entities.Role;
import com.minkatec.Blog.entities.User;
import com.minkatec.Blog.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired    UserDao userDao;
    @Autowired    MailService mailService;
    @Autowired    JwtService jwtService;
    @Autowired    ConfirmationCodeDao confirmationCodeDao;

    public void register(UserDto userDto) {
        if( userDao.findByEmailIgnoreCase(userDto.getEmail()).isPresent() ) {
            throw  new AlreadyExistException("The email: " + userDto.getEmail() + " Exist in app.");
            }
        if( userDao.findByUsernameIgnoreCase(userDto.getUsername()).isPresent() ) {
            throw  new AlreadyExistException("The username: " + userDto.getUsername() + " Exist in app.");
            }
        //TODO Reorganize order, first try to send email, generate code and then save all???
        User user = User.builder()
                .username(userDto.getUsername())
                .password(new BCryptPasswordEncoder().encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .name(userDto.getName())
                .active(false)
                .registrationDate(LocalDateTime.now())
                .roles(new Role[]{Role.AUTHENTICATED}) //TODO How to deal with writer role and admin role?
                .build();
        User createdUser =  userDao.save(user);

        ConfirmationCode confirmationToken = confirmationCodeDao.save(new ConfirmationCode(createdUser));

        mailService.sendMail(userDto.getEmail(),"Registration Code","Your activation code is: " + confirmationToken.getCode());

        // return new TokenOutputDto(jwtService.createToken(createdUser.getUsername(), createdUser.getName(),createdUser.getStringRoles()));
    }

    public void confirmUserAccount(String code){ //Todo  Should I also request the mail like parameter?
        ConfirmationCode confirmationCode = confirmationCodeDao.findByCode(code);
        if(code == null) {
            throw new ConfirmationUserException("Code confirmation null");
        }
        if (LocalDateTime.now().isAfter(confirmationCode.getExpiredDataToken())){
            throw new ConfirmationUserException("Code confirmation expired: " + confirmationCode.getExpiredDataToken());
        }

        User user = userDao
                .findByEmailIgnoreCase(confirmationCode.getUser().getEmail())
                .orElseThrow(()-> new NotFoundException("Not found user email"));
        user.setActive(true);
        userDao.save(user);
    }//Los confirm envian mensaje , devuelven algo?

    public TokenOutputDto login(String username) {
        User user = userDao.findByUsername(username)
                           .orElseThrow(() -> new RuntimeException("Username not found: " + username));

        String[] roles = Arrays.stream(user.getRoles()).map(Role::name).toArray(String[]::new);

        return new TokenOutputDto(jwtService.createToken(user.getUsername(), user.getName(),roles));
    }

    private String getCurrentUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User getCurrentUser(){
      return  userDao
              .findByUsername(this.getCurrentUsername())
              .orElseThrow(() -> new NotFoundException("User not found: " + this.getCurrentUsername()));
    }

}