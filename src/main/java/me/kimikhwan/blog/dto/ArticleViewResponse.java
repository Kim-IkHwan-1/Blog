package me.kimikhwan.blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kimikhwan.blog.domain.Blog;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ArticleViewResponse {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;

    public ArticleViewResponse(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.writer = blog.getWriter();
        this.createdAt = blog.getCreatedAt();
    }
}
