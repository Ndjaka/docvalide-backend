package com.ghosttech.dao;

import com.ghosttech.dto.UserRequest;
import com.ghosttech.model.User;


public interface UserDao {
    int insertUser(User user) ;
    Boolean checkifUserExist(UserRequest user);
}
