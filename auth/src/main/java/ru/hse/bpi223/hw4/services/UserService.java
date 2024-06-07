package ru.hse.bpi223.hw4.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hse.bpi223.hw4.models.User;
import ru.hse.bpi223.hw4.repositories.UserRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Service
public interface UserService {

    void saveUser(User usr);

    List<User> getAllUsers();

    User findByEmail(String email);
}
