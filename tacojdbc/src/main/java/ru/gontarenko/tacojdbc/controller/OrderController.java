package ru.gontarenko.tacojdbc.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.gontarenko.tacojdbc.entity.Order;
import ru.gontarenko.tacojdbc.repository.OrderRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/order")
@SessionAttributes("order")
public class OrderController {
    private final OrderRepository orderRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping("/current")
    public String orderForm(@ModelAttribute Order order) {
        log.info("ModelAttribute order: " + order);
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

        orderRepo.save(order);
        sessionStatus.setComplete();

        log.info("Order submitted: " + order);
        return "redirect:/";
    }
}
