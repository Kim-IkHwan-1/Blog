package me.kimikhwan.blog.service;

import jakarta.persistence.ManyToOne;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.kimikhwan.blog.domain.Blog;
import me.kimikhwan.blog.domain.User;
import me.kimikhwan.blog.dto.AddArticleRequest;
import me.kimikhwan.blog.dto.BlogListViewResponse;
import me.kimikhwan.blog.dto.UpdateArticleRequest;
import me.kimikhwan.blog.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // final이 붙거나 @Notnull이 붙은 필드의 생성자 추가
@Service
public class BlogService {

    private final BlogRepository blogRepository;


    // 블로그 글 추가
    public Blog save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    @Transactional
    public Blog saveForm(String title, String content, String writer) {
        Blog blog = new Blog(title, content, writer);
        return blogRepository.save(blog);
    }

    // JPA지원 메소드인 findAll()을 호풀해 article 테이블에 있는 모든 데이터를 조회
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    public List<BlogListViewResponse> findAllFiltered(String title, String writer) {
        return blogRepository.findAll().stream()
                .filter(blog -> (title == null || blog.getTitle().contains(title)) && // 제목 필터
                        (writer == null || blog.getWriter().equals(writer)))  // 작성자 필터
                .map(BlogListViewResponse::new)
                .collect(Collectors.toList());
    }

    public Blog findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional // 블로그 글 수정
    public Blog update(long id, UpdateArticleRequest request){
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found " + id));
        blog.update(request.getTitle(), request.getContent());

        return blog;
    }


}
