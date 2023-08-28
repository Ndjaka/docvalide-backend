package com.ghosttech.mapper.rowMapper;

import com.ghosttech.model.*;
import com.ghosttech.utils.FileManager;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ghosttech.utils.ConvertData.stringToObject;


public class CriminalRecordRowMapper implements RowMapper<CriminalRecordExtractManager> {

    @Override
    public CriminalRecordExtractManager mapRow(ResultSet rs, int rowNum) throws SQLException {
        CriminalRecordExtractManager criminalRecordExtractManager = new CriminalRecordExtractManager();

        Orders orders = new Orders();
        orders.setId(rs.getObject("order_id", java.util.UUID.class));
        orders.setOrderDate(rs.getTimestamp("order_date").toInstant());
        orders.setOrderStatus(rs.getString("order_status"));
        orders.setOrderAmount(rs.getInt("order_amount"));
        orders.setOrderNumber(rs.getString("order_number"));

        criminalRecordExtractManager.setOrders(orders);

        CriminalRecordExtract criminalRecordExtract = new CriminalRecordExtract();
        criminalRecordExtract.setId(rs.getObject("criminal_record_id", java.util.UUID.class));
        criminalRecordExtract.setMotherName(rs.getString("mother_name"));
        criminalRecordExtract.setBirthDepartment(rs.getString("birth_department"));
        criminalRecordExtract.setDate(rs.getTimestamp("criminal_record_date").toInstant());
        criminalRecordExtract.setEstablished(rs.getBoolean("is_established"));
        FileUrls fileUrl = (FileUrls) stringToObject(rs.getString("file_url"), FileUrls.class);

        criminalRecordExtract.setFileUrl(
                FileUrls.builder()
                        .backUrl(FileManager.getFile(fileUrl.getBackUrl()))
                        .frontUrl(FileManager.getFile(fileUrl.getFrontUrl()))
                        .fileName(null)
                        .build()
        );


        criminalRecordExtractManager.setCriminalRecordExtract(criminalRecordExtract);

        User user = new User();
        user.setId(rs.getObject("user_id", java.util.UUID.class));
        user.setFirstname(rs.getString("first_name"));
        user.setLastname(rs.getString("last_name"));
        user.setEmail((rs.getString("email")));
        user.setPhoneNumber((rs.getString("phone_number")));
        user.setTownOfResidence(rs.getString("town_of_residence"));
        user.setActive(rs.getBoolean("is_active"));

        criminalRecordExtractManager.setUser(user);

        return criminalRecordExtractManager;

    }
}
