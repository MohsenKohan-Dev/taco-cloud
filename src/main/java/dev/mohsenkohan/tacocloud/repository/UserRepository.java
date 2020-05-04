package dev.mohsenkohan.tacocloud.repository;

import dev.mohsenkohan.tacocloud.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
