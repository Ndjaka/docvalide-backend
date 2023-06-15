package com.ghosttech.service;

import com.ghosttech.dao.UserDao;
import com.ghosttech.dto.UserRequest;
import com.ghosttech.exception.NotFoundException;
import com.ghosttech.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private  final UserDao userDao;
    public User addUser(UserRequest userRequest)  {

        Boolean isUserExit = userDao.checkifUserExist(userRequest);
        if(isUserExit) throw new  NotFoundException(String.format("User %s is already exist", userRequest.getFirstname()),"USER_NOT_FOUND");

        var user = User.builder()
                .id(UUID.randomUUID())
                .email(userRequest.getEmail())
                .createdDate(Instant.now())
                .password(userRequest.getPassword())
                .phoneNumber(userRequest.getPhoneNumber())
                .roles(userRequest.getRoles())
                .isActive(true)
                .lastname(userRequest.getLastname())
                .firstname(userRequest.getFirstname())
                .occupation(userRequest.getOccupation())
                .townOfResidence(userRequest.getTownOfResidence())
                .build();

        int result = userDao.insertUser(user);
        if(result != 1 ) throw new IllegalStateException("oops somthing went wrong");

        return user;
    }
}
