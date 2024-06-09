package ru.hse.bpi223.hw4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hse.bpi223.hw4.dto.UserCredentials;
import ru.hse.bpi223.hw4.models.User;
import ru.hse.bpi223.hw4.repositories.UserRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Service
public interface UserService {

    /**
     * Handles saving user
     * @param user user
     */
    void saveUser(User user);

    /**
     * Handles registering new user
     * @param user user
     */
    void registerUser(User user);

    /**
     * Returns all users
     * @return all users
     */
    List<User> getAllUsers();

    /**
     * Finds user by email
     * @param email email
     * @return all users
     */
    User findByEmail(String email);

    /**
     * Checks that credentials are valid
     * @param credentials user credentials
     * @return are credentials valid
     */
    boolean checkCredentials(UserCredentials credentials);
}
