package ru.hse.bpi223.hw4.controllers;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.bpi223.hw4.dto.ExceptionResponse;
import ru.hse.bpi223.hw4.dto.JwtAuthenticationResponse;
import ru.hse.bpi223.hw4.dto.SignInRequest;
import ru.hse.bpi223.hw4.dto.SignUpRequest;
import ru.hse.bpi223.hw4.exceptions.*;
import ru.hse.bpi223.hw4.models.Session;
import ru.hse.bpi223.hw4.models.User;
import ru.hse.bpi223.hw4.security.jwt.JwtService;
import ru.hse.bpi223.hw4.services.AuthenticationService;
import ru.hse.bpi223.hw4.services.UserService;
import ru.hse.bpi223.hw4.validate.UserCredentialsValidator;
import java.security.NoSuchAlgorithmException;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;


    /**
     * Route for signing up
     * @param request request with nickname, email, password for new user
     * @return JWT token
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation=JwtAuthenticationResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {
                        @Content(mediaType = "application/json",
                                schema=@Schema(implementation=ExceptionResponse.class)
                        )
                    })
    })
    @GetMapping("/sign-up")
    public JwtAuthenticationResponse signUp(SignUpRequest request) throws NicknameFormatException, EmailFormatException, PasswordFormatException, ExistingEmailException {
        return authenticationService.signUp(request);
    }

    /**
     * Route for signing in
     * @param request request with email, password of user
     * @return JWT token
     */
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation=JwtAuthenticationResponse.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema=@Schema(implementation=ExceptionResponse.class)
                            )
                    })
    })
    @GetMapping("/sign-in")
    public JwtAuthenticationResponse signIn(SignInRequest request) throws UserNotFoundException, IncorrectCredentialsException {
        return authenticationService.signIn(request);
    }
}
