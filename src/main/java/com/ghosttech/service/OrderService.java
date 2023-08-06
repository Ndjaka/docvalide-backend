package com.ghosttech.service;

import com.ghosttech.constants.DocValidConstant;
import com.ghosttech.dao.OrdersDao;
import com.ghosttech.dto.OrderRequest;
import com.ghosttech.model.Orders;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;


@Service
public class OrderService  {
    private final OrdersDao ordersDao;

    public OrderService(OrdersDao ordersDao) {
        this.ordersDao = ordersDao;
    }

    /**
     * Add order.
     * @param orderRequest the order request
     * @param userId the user id
     *
     */
    public void addOrder(OrderRequest orderRequest, UUID userId) {

        var order = Orders.builder()
                .id(UUID.randomUUID())
                .orderType(orderRequest.getOrderType())
                .orderAmount(orderRequest.getOrderAmount())
                .orderStatus(DocValidConstant.PENDING)
                .orderNumber(orderRequest.getOrderNumber())
                .orderDate(Instant.now())
                .userId(userId)
                .build();

        int insertOrderId = ordersDao.insertOrder(order);
        if(insertOrderId != 1) throw new IllegalStateException("something went wrong when order was insert");

    }


}
