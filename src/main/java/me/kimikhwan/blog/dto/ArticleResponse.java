package me.kimikhwan.blog.dto;

import lombok.Getter;
import me.kimikhwan.blog.domain.Blog;

@Getter
public class ArticleResponse {

    private final String title;
    private final String content;

    public ArticleResponse(Blog blog){
        this.title = blog.getTitle();
        this.content = blog.getContent();
    }
}
