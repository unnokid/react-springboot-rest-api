package com.example.gccoffee.repository;

import com.example.gccoffee.model.Order;
import com.example.gccoffee.model.OrderItem;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class OrderJdbcRepository implements OrderRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public OrderJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Order insert(Order order) {
        jdbcTemplate.update("insert into orders(order_id, email, address, postcode, order_status, create_at, update_at)" +
                "values(UNHEX(REPLACE(:orderId, '-', '')), :email,:address, :postcode, :orderStatus, :createAt, :updateAt)",
                toOrderParamMap(order));
        order.getOrderItems()
                .forEach(item ->
                        jdbcTemplate.update("insert into order_items(order_id, product_id, category, price, quantity, create_at, update_at)"+
                "values (UNHEX(REPLACE(:orderId, '-', '')), UNHEX(REPLACE(:productId, '-', '')), :category,:price, :quantity, :createAt, :updateAt)",
                toOrderItemParamMap(order.getOrderId(),order.getCreateAt(),order.getUpdateAt(),item)));
        return order;
    }


    private Map<String, Object> toOrderParamMap(Order order) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("orderId", order.getOrderId().toString().getBytes());
        paramMap.put("email", order.getEmail().getAddress());
        paramMap.put("address", order.getAddress());
        paramMap.put("postcode", order.getPostcode());
        paramMap.put("orderStatus", order.getOrderStatus().toString());
        paramMap.put("createAt", order.getCreateAt());
        paramMap.put("updateAt", order.getUpdateAt());
        return paramMap;
    }

    private Map<String, Object> toOrderItemParamMap(UUID orderId, LocalDateTime createAt, LocalDateTime updateAt, OrderItem item) {
        var paramMap = new HashMap<String, Object>();
        paramMap.put("orderId",orderId.toString().getBytes());
        paramMap.put("productId",item.getProductId().toString().getBytes());
        paramMap.put("category",item.getCategory().toString());
        paramMap.put("price",item.getPrice());
        paramMap.put("quantity",item.getQuantity());
        paramMap.put("createAt",createAt);
        paramMap.put("updateAt",updateAt);

        return paramMap;
    }
}
