package ru.hse.bpi223.hw4.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

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
    @Size(max=50)
    private String nickname;


    @NotNull
    @Length(max=100)
    @Column(name = "email")
    @Size(max=100)
    private String email;


    @NotNull
    @Length(max=255)
    @Column(name = "password")
    @Size(min=8, max=255)
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
