package ru.gontarenko.tacojdbc.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.gontarenko.tacojdbc.entity.Order;
import ru.gontarenko.tacojdbc.entity.Taco;
import ru.gontarenko.tacojdbc.repository.OrderRepository;

import java.util.*;

@Repository
public class JdbcOrderRepository implements OrderRepository {
    private final SimpleJdbcInsert orderInserter;
    private final SimpleJdbcInsert orderTacoInserter;

    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbc) {
        this.orderInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("order_table")
                .usingGeneratedKeyColumns("id");
        this.orderTacoInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("taco_order");
    }

    @Override
    public Order save(Order order) {
        order.setPlacedAt(new Date());
        long orderId = mySaveOrderDetails(order);
        List<Taco> tacos = order.getTacos();
        tacos.forEach(taco -> saveTacoToOrder(taco, orderId));
        return order;
    }

    private void saveTacoToOrder(Taco taco, long orderId) {
        Map<String, Object> values = new HashMap<>() {{
            put("order_id", orderId);
            put("taco_id", taco.getId());
        }};
        orderTacoInserter.execute(values);
    }

    private long mySaveOrderDetails(Order order) {
        Map<String, Object> values = new LinkedHashMap<>() {{
            put("DELIVERY_NAME", order.getDeliveryName());
            put("DELIVERY_STREET", order.getDeliveryStreet());
            put("DELIVERY_CITY", order.getDeliveryCity());
            put("DELIVERY_STATE", order.getDeliveryState());
            put("DELIVERY_ZIP", order.getDeliveryZip());
            put("CC_NUMBER", order.getCcNumber());
            put("CC_EXPIRATION", order.getCcExpiration());
            put("CC_CVV", order.getCcCVV());
            put("PLACED_AT", order.getPlacedAt());
        }};
        return orderInserter.executeAndReturnKey(values).longValue();
    }
}