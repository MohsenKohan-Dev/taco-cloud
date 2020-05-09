package dev.mohsenkohan.tacocloud.part1.repository;

import dev.mohsenkohan.tacocloud.part1.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
