package com.minkatec.Blog.dtos;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor //Lombock

public class ArticleDto {
    @NotNull
    String title;
    @NotNull
    String content;
    @NotNull
    String summary;

    String[] tags ;
}
