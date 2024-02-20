package com.example.security20.service.impl;

import com.example.security20.dto.UserDTO;
import com.example.security20.entity.User;
import com.example.security20.entity.UserPhysicalParameters;
import com.example.security20.mapper.UserMapper;
import com.example.security20.repository.UserPhysicalParametersRepository;
import com.example.security20.repository.UserRepository;
import com.example.security20.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserPhysicalParametersRepository physicalParametersRepository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    @Override
    public UserDTO createUser(User user) {
//        User userToSave = mapper.convertToEntity(userDTO);
        user.setPassword(encoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return mapper.convertToDto(savedUser);
    }

    @Override
    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteUserPhysicalParameters(Long id) {
        physicalParametersRepository.deletePhysicalParametersByUserId(id);
    }

    @Override
    public void saveUserPhysicalParameters(UserPhysicalParameters userPhysicalParameters) {
        physicalParametersRepository.save(userPhysicalParameters);
    }



}
