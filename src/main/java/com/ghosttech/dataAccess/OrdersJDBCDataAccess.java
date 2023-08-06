package com.ghosttech.dataAccess;

import com.ghosttech.dao.OrdersDao;
import com.ghosttech.model.Orders;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
@AllArgsConstructor
public class OrdersJDBCDataAccess implements OrdersDao {

    JdbcTemplate jdbcTemplate;
    @Override
    public int insertOrder(Orders order) {
        String sql = """
                INSERT INTO orders(
                id,
                orderType,
                order_date,
                order_status,
                order_amount,
                order_number,
                user_id
                )
                VALUES(?,?,?,?,?,?,?)
                """;


        return jdbcTemplate.update(sql,
                order.getId(),
                order.getOrderType(),
                Timestamp.from(order.getOrderDate()),
                order.getOrderStatus(),
                order.getOrderAmount(),
                order.getOrderNumber(),
                order.getUserId()
        );
    }
}