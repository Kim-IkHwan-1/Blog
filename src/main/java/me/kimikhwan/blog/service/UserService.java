package me.kimikhwan.blog.service;

import lombok.RequiredArgsConstructor;
import me.kimikhwan.blog.domain.User;
import me.kimikhwan.blog.dto.AddUserRequest;
import me.kimikhwan.blog.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto){
        return userRepository.save(User.builder()
                .name(dto.getName())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
}
