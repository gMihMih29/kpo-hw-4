package ru.hse.bpi223.hw4.services;

import org.springframework.stereotype.Service;
import ru.hse.bpi223.hw4.models.User;
import ru.hse.bpi223.hw4.repositories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User usr) {
        userRepository.save(usr);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
