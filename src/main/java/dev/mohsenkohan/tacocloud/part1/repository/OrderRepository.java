package dev.mohsenkohan.tacocloud.part1.repository;

import dev.mohsenkohan.tacocloud.part1.domain.Order;
import dev.mohsenkohan.tacocloud.part1.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
