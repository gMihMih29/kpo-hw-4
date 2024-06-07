package ru.hse.bpi223.hw4.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.hse.bpi223.hw4.models.Session;

public interface SessionRepository extends CrudRepository<Session, Integer> {
}
