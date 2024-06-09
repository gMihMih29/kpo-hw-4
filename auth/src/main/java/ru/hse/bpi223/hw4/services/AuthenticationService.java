package ru.hse.bpi223.hw4.services;

import org.springframework.stereotype.Service;
import ru.hse.bpi223.hw4.dto.JwtAuthenticationResponse;
import ru.hse.bpi223.hw4.dto.SignInRequest;
import ru.hse.bpi223.hw4.dto.SignUpRequest;
import ru.hse.bpi223.hw4.exceptions.*;

@Service
public interface AuthenticationService {

    /**
     * Handles sign up request
     * @param request request with nickname, email and password
     * @return token for user
     */
    JwtAuthenticationResponse signUp(SignUpRequest request) throws NicknameFormatException, EmailFormatException, PasswordFormatException, ExistingEmailException;

    /**
     * Handles sign in request
     * @param request request with email and password
     * @return token for user
     */
    JwtAuthenticationResponse signIn(SignInRequest request) throws UserNotFoundException, IncorrectCredentialsException;
}
