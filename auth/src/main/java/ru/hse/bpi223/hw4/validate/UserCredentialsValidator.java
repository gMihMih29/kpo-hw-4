package ru.hse.bpi223.hw4.validate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.hse.bpi223.hw4.controllers.dto.UserSignInRequest;

@Component
public class UserCredentialsValidator {

    public boolean validateNickname(String nickname) {
        return true;
    }

    public boolean validateEmail(String email) {
        return true;
    }
    public boolean validatePassword(String password) {
        return true;
    }

}
