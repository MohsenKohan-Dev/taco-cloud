package dev.mohsenkohan.tacocloud.part2.api;

import dev.mohsenkohan.tacocloud.part1.domain.Order;
import dev.mohsenkohan.tacocloud.part1.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orders", produces = "application/json")
@CrossOrigin("*")
public class OrderRestController {

    private final OrderRepository orderRepository;

    public OrderRestController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public Order putOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/josn")
    public Order patchOrder(@PathVariable("orderId") Long orderId, @RequestBody Order patch) {
        Order order = orderRepository.findById(orderId).get();

        if (patch.getDeliveryName() != null)
            order.setDeliveryName(patch.getDeliveryName());

        if (patch.getDeliveryStreet() != null)
            order.setDeliveryStreet(patch.getDeliveryStreet());

        if (patch.getDeliveryCity() != null)
            order.setDeliveryCity(patch.getDeliveryCity());

        if (patch.getDeliveryState() != null)
            order.setDeliveryState(patch.getDeliveryState());

        if (patch.getDeliveryZip() != null)
            order.setDeliveryZip(patch.getDeliveryState());

        if (patch.getCcNumber() != null)
            order.setCcNumber(patch.getCcNumber());

        if (patch.getCcExpiration() != null)
            order.setCcExpiration(patch.getCcExpiration());

        if (patch.getCcCVV() != null)
            order.setCcCVV(patch.getCcCVV());

        return orderRepository.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
