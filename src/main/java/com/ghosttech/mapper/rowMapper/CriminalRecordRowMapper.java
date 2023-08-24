package com.ghosttech.mapper.rowMapper;

import com.ghosttech.model.*;
import com.ghosttech.utils.FileManager;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ghosttech.utils.ConvertData.stringToObject;


public class CriminalRecordRowMapper implements RowMapper<CriminalRecordExtractManager> {

   private final CriminalRecordExtractManager  criminalRecordExtractManager;

    public CriminalRecordRowMapper() {
        this.criminalRecordExtractManager = new CriminalRecordExtractManager();
    }
    @Override
    public CriminalRecordExtractManager mapRow(ResultSet rs, int rowNum) throws SQLException {

        Orders orders = new Orders();
        orders.setId(rs.getObject("id", java.util.UUID.class));
        orders.setOrderDate(rs.getTimestamp("orderdate").toInstant());
        orders.setOrderStatus(rs.getString("order_status"));
        orders.setOrderAmount(rs.getInt("order_amount"));
        orders.setOrderNumber(rs.getString("order_number"));

        criminalRecordExtractManager.setOrders(orders);

        CriminalRecordExtract criminalRecordExtract = new CriminalRecordExtract();
        criminalRecordExtract.setId(rs.getObject("criminalrecordid", java.util.UUID.class));
        criminalRecordExtract.setEstablished(rs.getBoolean("is_established"));
        criminalRecordExtract.setMotherName(rs.getString("mother_name"));
        criminalRecordExtract.setBirthDepartment(rs.getString("birth_department"));
        criminalRecordExtract.setDate(rs.getTimestamp("criminalrecorddate").toInstant());

        FileUrls fileUrl = (FileUrls) stringToObject(rs.getString("file_url"), FileUrls.class);

        criminalRecordExtract.setFileUrl(
                FileUrls.builder()
                        .backUrl(FileManager.getFile(fileUrl.getBackUrl()))
                        .frontUrl(FileManager.getFile(fileUrl.getFrontUrl()))
                        .fileName(FileManager.getFile(fileUrl.getFileName()))
                        .build()
        );


        criminalRecordExtractManager.setCriminalRecordExtract(criminalRecordExtract);

        User user = new User();
        user.setId(rs.getObject("userid", java.util.UUID.class));
        user.setFirstname(rs.getString("firstname"));
        user.setLastname(rs.getString("lastname"));
        user.setEmail((rs.getString("email")));
        user.setPhoneNumber((rs.getString("phone_number")));
        user.setTownOfResidence(rs.getString("town_of_residence"));
        user.setActive(rs.getBoolean("is_active"));

        criminalRecordExtractManager.setUser(user);

        return criminalRecordExtractManager;

    }
}
