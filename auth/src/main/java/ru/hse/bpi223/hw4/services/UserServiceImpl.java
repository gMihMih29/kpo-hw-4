package ru.hse.bpi223.hw4.services;

import org.springframework.stereotype.Service;
import ru.hse.bpi223.hw4.config.SecurityConfig;
import ru.hse.bpi223.hw4.dto.UserCredentials;
import ru.hse.bpi223.hw4.exceptions.IncorrectCredentialsException;
import ru.hse.bpi223.hw4.exceptions.UserNotFoundException;
import ru.hse.bpi223.hw4.models.User;
import ru.hse.bpi223.hw4.repositories.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Handles saving user
     * @param user user
     */
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    /**
     * Handles registering new user
     * @param user user
     */
    @Override
    public void registerUser(User user) {
        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        saveUser(user);
    }

    /**
     * Returns all users
     * @return all users
     */
    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * Finds user by email
     * @param email email
     * @return all users
     */
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Checks that credentials are valid
     * @param credentials user credentials
     * @return are credentials valid
     */
    @Override
    public boolean checkCredentials(UserCredentials credentials) {
        User usr = findByEmail(credentials.getEmail());
        if (usr == null) {
            return false;
        }
        return Objects.equals(usr.getPassword(), SecurityConfig.passwordEncoder().encode(credentials.getPassword()));
    }
}
