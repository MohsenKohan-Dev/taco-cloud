package dev.mohsenkohan.tacocloud.repository;

import dev.mohsenkohan.tacocloud.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
