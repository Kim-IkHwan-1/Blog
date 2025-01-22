package me.kimikhwan.blog.service;

import lombok.RequiredArgsConstructor;
import me.kimikhwan.blog.repository.UserRepository;
import me.kimikhwan.blog.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
// 스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // 사용자 이름(name)으로 사용자의 정보를 가져오는 메서드
    @Override
    public User loadUserByUsername(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException((name)));
    }
}
