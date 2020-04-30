package com.minkatec.Diary.business_controllers;

import com.minkatec.Diary.business_services.JwtService;
import com.minkatec.Diary.daos.DiaryDao;
import com.minkatec.Diary.daos.UserDao;
import com.minkatec.Diary.dtos.DiaryDto;
import com.minkatec.Diary.entities.Diary;
import com.minkatec.Diary.entities.User;
import com.minkatec.Diary.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class DiaryController {
    @Autowired    DiaryDao diaryDao;
    @Autowired    JwtService jwtService;
    @Autowired    UserDao userDao;
    @Autowired    UserController userController;


    public DiaryDto create(DiaryDto diaryDto){

        User user = userDao.findByUsername("blopez")
                .orElseThrow(()-> new NotFoundException("Not found User"));
        //Todo Buscar el usuario desde el token
        //TODO poner ese metodo en usuario controller, o en un servicio


        Diary newDiary = Diary.builder()
                .title(diaryDto.getTitle())
                .registrationDate(LocalDateTime.now())
                .user(user)
                .build();

        return  new DiaryDto(diaryDao.save(newDiary)) ;
    }

}
