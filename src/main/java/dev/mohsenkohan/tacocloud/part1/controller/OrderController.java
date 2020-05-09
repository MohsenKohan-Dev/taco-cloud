package dev.mohsenkohan.tacocloud.part1.controller;

import dev.mohsenkohan.tacocloud.part1.domain.Order;
import dev.mohsenkohan.tacocloud.part1.domain.User;
import dev.mohsenkohan.tacocloud.part1.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderProps orderProps;

    public OrderController(OrderRepository orderRepository, OrderProps orderProps) {
        this.orderRepository = orderRepository;
        this.orderProps = orderProps;
    }

    @GetMapping("/current")
    public String orderForm(Order order, @AuthenticationPrincipal User user) {
        if (order.getDeliveryName() == null) {
            order.setDeliveryName(user.getFullName());
            order.setDeliveryStreet(user.getStreet());
            order.setDeliveryCity(user.getCity());
            order.setDeliveryState(user.getState());
            order.setDeliveryZip(user.getZip());
        }
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();

        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders",
                orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }
}
