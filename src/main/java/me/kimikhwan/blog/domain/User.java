package me.kimikhwan.blog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , updatable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "password")
    private String password;


    @Builder
    public User(String name, String password, String phone_number){
        this.name = name;
        this.phone_number = phone_number;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override // 사용자 id(이름)반환
    public String getUsername(){
        return name;
    }

    @Override // 사용자 비밀번호 반환
    public String getPassword(){
        return password;
    }

    @Override // 계정 만료 여부 확인
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override // 계정 잠금 여부 반환
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override // 비밀번호 만료 여부 반환
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override // 계정 사용 가능 여부 반호나
    public boolean isEnabled(){
        return true;
    }
}
