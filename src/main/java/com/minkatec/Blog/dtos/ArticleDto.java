package com.minkatec.Blog.dtos;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    @NotNull
   // @Min(1)
    String title;
    @NotNull
    String content;
    String[] tags ;
    @NotNull
    String summary;
}
