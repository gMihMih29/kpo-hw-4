package ru.hse.bpi223.hw4.services;

import org.springframework.stereotype.Service;
import ru.hse.bpi223.hw4.config.SecurityConfig;
import ru.hse.bpi223.hw4.dto.JwtAuthenticationResponse;
import ru.hse.bpi223.hw4.dto.SignInRequest;
import ru.hse.bpi223.hw4.dto.SignUpRequest;
import ru.hse.bpi223.hw4.dto.UserCredentials;
import ru.hse.bpi223.hw4.exceptions.*;
import ru.hse.bpi223.hw4.models.Session;
import ru.hse.bpi223.hw4.models.User;
import ru.hse.bpi223.hw4.security.jwt.JwtService;
import ru.hse.bpi223.hw4.validate.UserCredentialsValidator;

import java.util.Objects;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserCredentialsValidator validator;

    public AuthenticationServiceImpl(UserService userService, JwtService jwtService, UserCredentialsValidator validator) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.validator = validator;
    }

    /**
     * Handles sign up request
     * @param request request with nickname, email and password
     * @return token for user
     */
    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) throws NicknameFormatException, EmailFormatException, PasswordFormatException, ExistingEmailException {
        if (!validator.validateNickname(request.getNickname())) {
            throw new NicknameFormatException("Wrong nickname format");
        }
        if (!validator.validateEmail(request.getEmail())) {
            throw new EmailFormatException("Wrong email format");
        }
        if (!validator.validatePassword(request.getPassword())) {
            throw new PasswordFormatException("Wrong password format");
        }
        if (userService.findByEmail(request.getEmail()) != null) {
            throw new ExistingEmailException("User with such email already exists");
        }
        User usr = new User(request.getNickname(), request.getEmail(), request.getPassword());
        userService.registerUser(usr);
        return new JwtAuthenticationResponse(jwtService.generateToken(usr));
    }

    /**
     * Handles sign in request
     * @param request request with email and password
     * @return token for user
     */
    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) throws UserNotFoundException, IncorrectCredentialsException {
        if (!userService.checkCredentials(new UserCredentials(request.getEmail(), request.getPassword()))) {
            throw new IncorrectCredentialsException("Incorrect credentials");
        }
        User usr = userService.findByEmail(request.getEmail());
        Session session = jwtService.findByUser(usr);
        if (session == null) {
            return new JwtAuthenticationResponse(jwtService.generateToken(usr));
        }
        return new JwtAuthenticationResponse(session.getToken());
    }
}
