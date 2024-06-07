package ru.hse.bpi223.hw4.controllers;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.bpi223.hw4.config.SecurityConfig;
import ru.hse.bpi223.hw4.controllers.dto.UserSignInRequest;
import ru.hse.bpi223.hw4.exceptions.EmailFormatException;
import ru.hse.bpi223.hw4.exceptions.ExistingEmailException;
import ru.hse.bpi223.hw4.exceptions.NicknameFormatException;
import ru.hse.bpi223.hw4.exceptions.PasswordFormatException;
import ru.hse.bpi223.hw4.models.User;
import ru.hse.bpi223.hw4.services.UserService;
import ru.hse.bpi223.hw4.validate.UserCredentialsValidator;
import java.security.NoSuchAlgorithmException;

@RestController
@AllArgsConstructor
public class AuthController {
    private final UserCredentialsValidator validator;
    private final UserService userService;

    @GetMapping("/sign-in")
    public void signIn(UserSignInRequest req) throws NicknameFormatException, EmailFormatException, PasswordFormatException, NoSuchAlgorithmException, ExistingEmailException {
        if (!validator.validateNickname(req.getNickname())) {
            throw new NicknameFormatException("Wrong nickname format");
        }
        if (!validator.validateEmail(req.getEmail())) {
            throw new EmailFormatException("Wrong email format");
        }
        if (!validator.validatePassword(req.getPassword())) {
            throw new PasswordFormatException("Wrong password format");
        }
        if (userService.findByEmail(req.getEmail()) != null) {
            throw new ExistingEmailException("User with such email already exists");
        }
        String hashPassword = SecurityConfig.passwordEncoder().encode(req.getPassword());
        User usr = User.builder()
                .email(req.getEmail())
                .nickname(req.getNickname())
                .password(hashPassword)
                .build();
        userService.saveUser(usr);
    }
}
