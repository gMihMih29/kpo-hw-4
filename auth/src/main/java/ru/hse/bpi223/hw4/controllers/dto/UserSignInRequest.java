package ru.hse.bpi223.hw4.controllers.dto;

import lombok.Data;

@Data
public class UserSignInRequest {
    private String nickname;
    private String email;
    private String password;
}
