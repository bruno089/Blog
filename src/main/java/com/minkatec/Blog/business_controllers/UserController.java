package com.minkatec.Blog.business_controllers;

import com.minkatec.Blog.business_services.JwtService;
import com.minkatec.Blog.business_services.MailService;
import com.minkatec.Blog.business_services.RandomGeneratorService;
import com.minkatec.Blog.daos.ConfirmationCodeDao;
import com.minkatec.Blog.daos.UserDao;
import com.minkatec.Blog.dtos.TokenOutputDto;
import com.minkatec.Blog.dtos.UserDto;
import com.minkatec.Blog.entities.ConfirmationCode;
import com.minkatec.Blog.entities.Role;
import com.minkatec.Blog.entities.User;
import com.minkatec.Blog.exceptions.ConfirmationUserException;
import com.minkatec.Blog.exceptions.ForbiddenException;
import com.minkatec.Blog.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    public TokenOutputDto register(UserDto userDto) {

        User user = User.builder()
                .username(userDto.getUsername())
                .password(new BCryptPasswordEncoder().encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .name(userDto.getName())
                .active(false)
                .registrationDate(LocalDateTime.now())
                .roles(new Role[]{Role.AUTHENTICATED})
                .build();

        User userCreated =  userDao.save(user);

        String[] roles = Arrays.stream(userCreated.getRoles()).map(Role::name).toArray(String[]::new);


        ConfirmationCode confirmationToken = new ConfirmationCode(user);
        confirmationCodeDao.save(confirmationToken);


        String code = RandomGeneratorService.generateRandomString();

        mailService.sendMail(
                userDto.getEmail(),
                "Registration Code",
                "Your activation code is: " + code);

        return new TokenOutputDto(jwtService.createToken(userCreated.getUsername(), userCreated.getName(),roles));
    }

    public TokenOutputDto login(String username) {
        User user = userDao.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Unexpected!!. Username not found:" + username));
        String[] roles = Arrays.stream(user.getRoles()).map(Role::name).toArray(String[]::new);

        return new TokenOutputDto(jwtService.createToken(user.getUsername(), user.getName(),roles));
    }

    @PreAuthorize("authenticated")
    public void confirmUserAccount(String code){
        ConfirmationCode confirmationCode = confirmationCodeDao.findByCode(code);
        if(code != null)
        {
            User user = userDao
                    .findByEmailIgnoreCase(confirmationCode.getUser().getEmail())
                    .orElseThrow(()-> new NotFoundException("Not found user email"));
            user.setActive(true);
            userDao.save(user);

        }else{
          throw new ConfirmationUserException("Code null");
        }
    }

    private String getCurrentUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User getCurrentUser(){
      return  userDao
              .findByUsername(this.getCurrentUsername())
              .orElseThrow(() -> new NotFoundException("User not found: " + this.getCurrentUsername()));
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

}