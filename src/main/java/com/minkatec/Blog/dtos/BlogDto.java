package com.minkatec.Blog.dtos;

import com.minkatec.Blog.entities.Blog;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {
    @NotEmpty
    String title;

    public BlogDto(Blog diary) {
        this.title = diary.getTitle();
    }
}
