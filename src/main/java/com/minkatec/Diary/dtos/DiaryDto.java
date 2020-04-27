package com.minkatec.Diary.dtos;

import com.minkatec.Diary.entities.Diary;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiaryDto {

    @NotEmpty
    String title;

    public DiaryDto(Diary diary) {
        this.title = diary.getTitle();
    }
}
