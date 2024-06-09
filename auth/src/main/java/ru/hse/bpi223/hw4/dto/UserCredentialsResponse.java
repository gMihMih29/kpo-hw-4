package ru.hse.bpi223.hw4.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.hse.bpi223.hw4.models.User;

@Data
@AllArgsConstructor
public class UserCredentialsResponse {
    private User user;
}
