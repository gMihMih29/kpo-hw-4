package ru.hse.bpi223.hw4.models;

import jakarta.persistence.*;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Length(max=255)
    @Column(name = "token")
    private String token;

    @NotNull
    @Column(name = "expires")
    private LocalDateTime expires;
}
