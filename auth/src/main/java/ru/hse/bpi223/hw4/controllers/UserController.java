package ru.hse.bpi223.hw4.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.bpi223.hw4.dto.ExceptionResponse;
import ru.hse.bpi223.hw4.dto.UserCredentialsResponse;
import ru.hse.bpi223.hw4.dto.UserDataRequest;
import ru.hse.bpi223.hw4.exceptions.UserNotFoundException;
import ru.hse.bpi223.hw4.security.jwt.JwtService;

@RestController
public class UserController {
    private final JwtService jwtService;

    public UserController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    /**
     * Route for getting user by token
     * @param request request with token
     * @return User credentials
     */
    @GetMapping("/users/find-by-token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= UserCredentialsResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation= ExceptionResponse.class)
                            )
                    })
    })
    public UserCredentialsResponse getUserByToken(UserDataRequest request) throws UserNotFoundException {
        return new UserCredentialsResponse(jwtService.findUserByToken(request.getToken()));
    }
}
