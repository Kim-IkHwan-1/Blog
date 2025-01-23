package me.kimikhwan.blog.controller;

import lombok.RequiredArgsConstructor;
import me.kimikhwan.blog.domain.Blog;
import me.kimikhwan.blog.dto.ArticleViewResponse;
import me.kimikhwan.blog.dto.BlogListViewResponse;
import me.kimikhwan.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/")
    public String root() {
        return "redirect:/blogs";
    }

    @GetMapping("/blogs")
    public String getArticle(Model model){
    List<BlogListViewResponse> blog = blogService.findAll().stream()
            .map(BlogListViewResponse::new)
            .toList();

    model.addAttribute("blog", blog); // 블로그 글 리스트 저장

    return "blog-list"; // blog-list.html 라는 html 뷰 조회

    }

    @GetMapping("/blogs/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model) {
        Blog blog = blogService.findById(id);
        model.addAttribute("blog", new ArticleViewResponse(blog));

        return "blog";
    }

    @GetMapping("/blogs/create-form")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null){
            model.addAttribute("blog", new ArticleViewResponse());
        } else {
            Blog blog = blogService.findById(id);
            model.addAttribute("blog",new ArticleViewResponse(blog));
        }

        return "create-form";
    }


}
