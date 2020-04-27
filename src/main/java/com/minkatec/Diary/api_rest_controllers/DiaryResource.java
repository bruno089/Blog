package com.minkatec.Diary.api_rest_controllers;

import com.minkatec.Diary.business_controllers.DiaryController;
import com.minkatec.Diary.dtos.DiaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping(DiaryResource.DIARIES)
public class DiaryResource {
    public static final String DIARIES = "/diaries";

    @Autowired    DiaryController diaryController;


    @PostMapping
    public DiaryDto create(@Valid @RequestBody DiaryDto diaryDto){
        return diaryController.create(diaryDto);
    }
}
