package me.kimikhwan.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {
    private String name;
    private String password;
    private String phoneNumber;
}
