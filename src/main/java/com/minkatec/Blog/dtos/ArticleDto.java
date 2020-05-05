package com.minkatec.Blog.dtos;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor //Lombock

public class ArticleDto {
    @NotNull
    @Min(1)
    String title;
    @NotNull
    String content;
    String[] tags ;
    @NotNull
    String summary;
}
