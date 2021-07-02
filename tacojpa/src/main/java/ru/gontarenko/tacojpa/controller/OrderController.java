package ru.gontarenko.tacojpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.gontarenko.tacojpa.entity.Order;
import ru.gontarenko.tacojpa.repository.OrderRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/order")
@SessionAttributes("order")
public class OrderController {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String currentOrder(@ModelAttribute Order order) {
        return "order";
    }

    @PostMapping
    public String processOrder(
            @Valid Order order, Errors errors,
            SessionStatus sessionStatus
    ) {
        if (errors.hasErrors()) {
            return "order";
        }
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/home";
    }
}
