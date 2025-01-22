package me.kimikhwan.blog.controller;

import lombok.RequiredArgsConstructor;
import me.kimikhwan.blog.domain.Blog;
import me.kimikhwan.blog.dto.AddArticleRequest;
import me.kimikhwan.blog.dto.ArticleResponse;
import me.kimikhwan.blog.dto.UpdateArticleRequest;
import me.kimikhwan.blog.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;


    // 블로그 글 생성
    @PostMapping("/api/blogs")
    //@RequestBody로 요청 본문 값 매핑
    public ResponseEntity<Blog> addArticle(@RequestBody AddArticleRequest request){
        Blog savedBlog = blogService.save(request);

        // 요청한 자원이 성공적으로 생성되었으며 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedBlog);
    }

    // GET요청이 오면 findAll()메서드 호출 -> ArticleResponse객체로 파싱 -> body에 담아 클라이언트에 전송
    @GetMapping("/api/blogs")
    public ResponseEntity<List<ArticleResponse>> findAllBlog(){
        List<ArticleResponse> blog = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(blog);
    }

    // 블로그 글 조회
    @GetMapping("/api/blogs/{id}")
    //URL 경로에서 값 추출
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable("id") long id) {
        Blog blog = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(blog));
    }

    // 블로그 글 삭제
    @DeleteMapping("/api/blogs/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    // 블로그 글 수정
    @PutMapping("/api/blogs/{id}")
    public ResponseEntity<Blog> updateArticle(@PathVariable("id") long id,
                                              @RequestBody UpdateArticleRequest request){
        Blog updateArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updateArticle);
    }


}


