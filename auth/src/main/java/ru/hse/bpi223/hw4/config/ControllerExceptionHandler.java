package ru.hse.bpi223.hw4.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.hse.bpi223.hw4.dto.ExceptionResponse;
import ru.hse.bpi223.hw4.exceptions.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * Handles exceptions thrown in controllers
 */
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EmailFormatException.class)
    public ResponseEntity<ExceptionResponse> handleEmailFormatException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(ex.getMessage()));
    }
    @ExceptionHandler(NicknameFormatException.class)
    public ResponseEntity<ExceptionResponse> handleNicknameFormatException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(ex.getMessage()));
    }
    @ExceptionHandler(PasswordFormatException.class)
    public ResponseEntity<ExceptionResponse> handlePasswordFormatException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(ExistingEmailException.class)
    public ResponseEntity<ExceptionResponse> handleExistingEmailException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler({UsernameNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleUserNotFoundException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(ex.getMessage()));
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleIncorrectCredentialsException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(ex.getMessage()));
    }
}
