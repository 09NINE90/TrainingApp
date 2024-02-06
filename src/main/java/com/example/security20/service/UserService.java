package com.example.security20.service;

import com.example.security20.dto.CustomUserDetails;
import com.example.security20.dto.UserDTO;
import com.example.security20.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> findAllUsers();


}
