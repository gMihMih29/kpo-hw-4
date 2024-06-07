package ru.hse.bpi223.hw4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.bpi223.hw4.models.User;
import ru.hse.bpi223.hw4.repositories.UserRepository;
import ru.hse.bpi223.hw4.services.UserService;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
