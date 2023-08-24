package com.ghosttech.dataAccess;

import com.ghosttech.dao.UserDao;
import com.ghosttech.dto.UserRequest;
import com.ghosttech.mapper.rowMapper.UserRowMapper;
import com.ghosttech.model.User;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;


@Repository()
@AllArgsConstructor
public class UserJDBCDataAccess implements UserDao {
    private final JdbcTemplate jdbcTemplate;
    @Override
    public int insertUser(User user) {
        String sql = """
                        INSERT INTO users(
                            id,
                            firstname,
                            lastname,
                            email,
                            password,
                            phone_number,
                            town_of_residence,
                            is_active,
                            roles,
                            occupation,
                            createdDate
                        )
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                    """;

        return   jdbcTemplate.update(
                sql,
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getTownOfResidence(),
                user.isActive(),
                user.getRoles(),
                user.getOccupation(),
                Timestamp.from(user.getCreatedDate())
                );
    }

    @Override
    public Boolean checkifUserExist(UserRequest userRequest) {

        String sql = """
                        SELECT
                            CASE
                                WHEN count(*) > 0 THEN TRUE
                                ELSE FALSE
                            END AS is_avaible
                        FROM users
                        WHERE email = ? OR phone_number = ?;
                    """;

        return jdbcTemplate.queryForObject(
                sql,
                Boolean.class,
                userRequest.getEmail(),
                userRequest.getPhoneNumber()
        );
    }

    @Override
    public Optional<User> getUserByEmail(String email) {

        String sql = """
                        SELECT
                           *
                        FROM users
                        WHERE email = ?;
                    """;

        return jdbcTemplate
                .query(sql, new UserRowMapper(), email)
                .stream()
                .findFirst();
    }

    @Override
    public Optional<User> getUserById(UUID userId) {
        String sql = """
                        SELECT
                           *
                        FROM users
                        WHERE id = ?;
                    """;

        return jdbcTemplate
                .query(sql, new UserRowMapper(), userId)
                .stream()
                .findFirst();
    }
}
