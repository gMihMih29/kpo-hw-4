package ru.hse.bpi223.hw4.models;

import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Length(max=50)
    @Column(name = "nickname")
    private String nickname;


    @NotNull
    @Length(max=100)
    @Column(name = "email")
    private String email;


    @NotNull
    @Length(max=255)
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "created")
    private Timestamp created;

    public User(String nickname, String email, String password) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.created = new Timestamp(System.currentTimeMillis());
    }

}
