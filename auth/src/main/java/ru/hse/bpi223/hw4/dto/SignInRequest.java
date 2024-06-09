package ru.hse.bpi223.hw4.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignInRequest {
    @Schema(description="User email")
    @Size(max=100)
    private String email;

    @Schema(description="User password")
    @Size(min=8, max=255)
    private String password;
}
