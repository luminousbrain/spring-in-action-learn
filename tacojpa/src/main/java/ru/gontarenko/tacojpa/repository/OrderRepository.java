package ru.gontarenko.tacojpa.repository;

import org.springframework.data.repository.CrudRepository;
import ru.gontarenko.tacojpa.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}