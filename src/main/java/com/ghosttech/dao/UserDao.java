package com.ghosttech.dao;

import com.ghosttech.dto.UserRequest;
import com.ghosttech.model.User;

import java.util.Optional;


public interface UserDao {
    int insertUser(User user) ;
    Boolean checkifUserExist(UserRequest user);

    Optional<User> getUserByEmail(String email);
}
