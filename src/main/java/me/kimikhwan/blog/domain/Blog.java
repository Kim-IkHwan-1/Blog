package me.kimikhwan.blog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import me.kimikhwan.blog.domain.User;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Blog {
    @Id // id를 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 1씩 자동 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "writer", updatable = false)
    private String writer;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate // 엔티티 생성 시간 저장
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // 엔티티 수정 시간 저장
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;



    @Builder // 빌더 패턴으로 객체 생성
    public Blog(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}