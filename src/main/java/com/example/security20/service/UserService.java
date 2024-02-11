package com.example.security20.service;

import com.example.security20.dto.UserDTO;
import com.example.security20.entity.UserPhysicalParameters;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> findAllUsers();
    void saveUserPhysicalParameters(UserPhysicalParameters userPhysicalParameters);

}
