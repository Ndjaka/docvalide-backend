package com.ghosttech.dao;

import com.ghosttech.model.Orders;
import org.springframework.stereotype.Repository;



public interface OrdersDao {
    int insertOrder(Orders order);
}
