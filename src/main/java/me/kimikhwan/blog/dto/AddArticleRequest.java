package me.kimikhwan.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kimikhwan.blog.domain.Blog;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;
    private String content;
    private String writer;
    public Blog toEntity() {
        return Blog.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }

}