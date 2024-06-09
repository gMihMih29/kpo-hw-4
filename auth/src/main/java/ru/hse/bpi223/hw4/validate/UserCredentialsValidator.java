package ru.hse.bpi223.hw4.validate;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Component
public class UserCredentialsValidator {
    private static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    /**
     * Checks that nickname is valid
     * @param nickname nickname to check
     * @return is nickname valid
     */
    public boolean validateNickname(String nickname) {
        return !nickname.isEmpty();
    }

    /**
     * Checks that email is valid
     * @param email email to check
     * @return is email valid
     */
    public boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Checks that password is valid
     * @param password password to check
     * @return is password valid
     */
    public boolean validatePassword(String password) {
        if (password.length() < 8) {
            return false;
        }
        boolean foundUpper = false;
        boolean foundLower = false;
        boolean foundSpecial = false;
        boolean foundDigit = false;
        for (int i = 0; i < password.length(); ++i) {
            if (password.charAt(i) > 'z') {
                return false;
            }
            if ('A' <= password.charAt(i) && password.charAt(i) <= 'Z') {
                foundUpper = true;
                continue;
            }
            if ('a' <= password.charAt(i) && password.charAt(i) <= 'z') {
                foundLower = true;
                continue;
            }
            if ('0' <= password.charAt(i) && password.charAt(i) <= '9') {
                foundDigit = true;
                continue;
            }
            foundSpecial = true;
        }
        return foundUpper && foundLower && foundSpecial && foundDigit;
    }

}
