package com.minkatec.Blog.dtos;

import com.minkatec.Blog.entities.Blog;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogDto {
  //  @NotEmpty
    String title;
    String description;
    int id;
    //TODO Cantidad de Articles , Owner ,

    public BlogDto(Blog blog) {
        this.title = blog.getTitle();
        this.description = blog.getDescription();
        this.id = blog.getId();

    }
}
