package com.example.security20.service;

import com.example.security20.dto.UserDTO;
import com.example.security20.entity.User;
import com.example.security20.entity.UserPhysicalParameters;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public UserDTO createUser(User user);

    public List<User> findAllUsers() ;
    void saveUserPhysicalParameters(UserPhysicalParameters userPhysicalParameters);
    public Optional<User> getUserById(Long id);
    void deleteUser(Long id);

    void deleteUserPhysicalParameters(Long id);
}
