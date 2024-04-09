package com.example.security20.service;

import com.example.security20.dto.UserDTO;
import com.example.security20.entity.User;
import com.example.security20.entity.UserPhysicalParameters;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO createUser(User user);

    List<User> findAllUsers() ;
    void saveUserPhysicalParameters(UserPhysicalParameters userPhysicalParameters);
    Optional<User> getUserById(Long id);
    void deleteUser(Long id);

    void deleteUserPhysicalParameters(Long id);

    List<User> getUserByRole(String role, Long id);

}
