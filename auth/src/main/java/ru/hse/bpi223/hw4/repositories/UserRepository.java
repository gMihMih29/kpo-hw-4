package ru.hse.bpi223.hw4.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.hse.bpi223.hw4.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);

}
