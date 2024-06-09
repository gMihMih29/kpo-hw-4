package ru.hse.bpi223.hw4.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import ru.hse.bpi223.hw4.exceptions.UserNotFoundException;
import ru.hse.bpi223.hw4.models.Session;
import ru.hse.bpi223.hw4.models.User;

public interface JwtService {
    /**
     * Checks that token is related to user and is not expired
     * @param token token to validate
     * @param user user for checking token
     * @return is token valid
     */
    boolean validate(String token, User user);

    /**
     * Generates token for user
     * @param user user who needs token
     * @return new token
     */
    String generateToken(User user);

    /**
     * Finds active session by user
     * @param user user
     * @return Active session if it exists, or null
     */
    Session findByUser(User user);

    /**
     * Finds user by token
     * @param token token
     * @return user
     */
    User findUserByToken(String token) throws UserNotFoundException;
}
