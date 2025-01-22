package me.kimikhwan.blog.dto;

import lombok.Getter;
import me.kimikhwan.blog.domain.Blog;

@Getter
public class BlogListViewResponse {
    private final Long id;
    private final String title;
    private final String content;

    public BlogListViewResponse(Blog blog){
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.content = blog.getContent();
    }
}
