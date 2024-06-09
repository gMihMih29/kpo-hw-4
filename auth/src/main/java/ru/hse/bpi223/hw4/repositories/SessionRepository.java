package ru.hse.bpi223.hw4.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.hse.bpi223.hw4.models.Session;
import ru.hse.bpi223.hw4.models.User;

import java.util.List;

public interface SessionRepository extends CrudRepository<Session, Integer> {
    Session findByToken(String token);
    List<Session> findByUser(User user);
}
