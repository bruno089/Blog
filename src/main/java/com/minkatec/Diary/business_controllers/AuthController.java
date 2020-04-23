package com.minkatec.Diary.business_controllers;

import com.minkatec.Diary.data_services.AuthService;
import com.minkatec.Diary.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserDto userDto){
        authService.signup(userDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
