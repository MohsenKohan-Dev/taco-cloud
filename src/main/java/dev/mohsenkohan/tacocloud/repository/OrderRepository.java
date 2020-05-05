package dev.mohsenkohan.tacocloud.repository;

import dev.mohsenkohan.tacocloud.domain.Order;
import dev.mohsenkohan.tacocloud.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
