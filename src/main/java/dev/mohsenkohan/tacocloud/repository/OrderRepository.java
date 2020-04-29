package dev.mohsenkohan.tacocloud.repository;

import dev.mohsenkohan.tacocloud.domain.Order;

public interface OrderRepository {

    Order save(Order order);
}
