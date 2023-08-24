package com.ghosttech.mapper.rowMapper;

import com.ghosttech.model.Legalization;
import com.ghosttech.model.LegalizationOrderManager;
import com.ghosttech.model.Orders;
import com.ghosttech.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LegalizationRowMapper implements RowMapper<LegalizationOrderManager> {


    @Override
    public LegalizationOrderManager mapRow(ResultSet rs, int rowNum) throws SQLException {

        LegalizationOrderManager legalizationOrderManager = new LegalizationOrderManager();

        var user = new User();

        user.setId(rs.getObject("userid", java.util.UUID.class));
        user.setFirstname(rs.getString("firstname"));
        user.setLastname(rs.getString("lastname"));
        user.setEmail((rs.getString("email")));
        user.setPhoneNumber((rs.getString("phone_number")));
        user.setTownOfResidence(rs.getString("town_of_residence"));
        user.setActive(rs.getBoolean("is_active"));

        legalizationOrderManager.setUser(user);

        var orders = new Orders();
        orders.setId((rs.getObject("orderid", java.util.UUID.class)));
        orders.setOrderDate(rs.getTimestamp("orderdate").toInstant());
        orders.setOrderStatus(rs.getString("order_status"));
        orders.setOrderAmount(rs.getInt("order_amount"));
        orders.setOrderNumber(rs.getString("order_number"));

        legalizationOrderManager.setOrders(orders);

        var legalization = new Legalization();
        legalization.setId(rs.getObject("legalizationid", java.util.UUID.class));
        legalization.setMotif(rs.getString("motif"));
        legalization.setReceipMoment(rs.getString("receip_moment"));
        legalization.setLegalized(rs.getBoolean("islegalized"));
        legalization.setQuantity(rs.getInt("quantity"));
        legalization.setDate(rs.getTimestamp("legalizationdate").toInstant());

        legalizationOrderManager.setLegalization(legalization);


        return legalizationOrderManager;
    }
}
