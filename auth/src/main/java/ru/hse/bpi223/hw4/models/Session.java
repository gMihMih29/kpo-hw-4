package ru.hse.bpi223.hw4.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "session")
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Length(max=255)
    @Column(name = "token")
    @Size(max=255)
    private String token;

    @NotNull
    @Column(name = "expires")
    private Timestamp expires;

    public Session(User usr, String token, Timestamp expires) {
        this.user = usr;
        this.token = token;
        this.expires = expires;
    }
}
