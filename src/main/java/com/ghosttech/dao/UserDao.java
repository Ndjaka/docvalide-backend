package com.ghosttech.dao;

import com.ghosttech.dto.UserRequest;
import com.ghosttech.model.User;

import java.util.Optional;
import java.util.UUID;


public interface UserDao {
    int insertUser(User user) ;
    Boolean checkifUserExist(UserRequest user);

    Optional<User> getUserByEmail(String email);
    Optional<User> getUserById(UUID  userId);


}
