package ru.hse.bpi223.hw4.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.hse.bpi223.hw4.exceptions.EmailFormatException;
import ru.hse.bpi223.hw4.exceptions.ExistingEmailException;
import ru.hse.bpi223.hw4.exceptions.NicknameFormatException;
import ru.hse.bpi223.hw4.exceptions.PasswordFormatException;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(EmailFormatException.class)
    public ResponseEntity<String> handleEmailFormatException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(NicknameFormatException.class)
    public ResponseEntity<String> handleNicknameFormatException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(PasswordFormatException.class)
    public ResponseEntity<String> handlePasswordFormatException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ExistingEmailException.class)
    public ResponseEntity<String> handleExistingEmailException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
