package ru.gontarenko.tacojdbc.repository;

import ru.gontarenko.tacojdbc.entity.Order;

public interface OrderRepository {
    Order save(Order order);
}
