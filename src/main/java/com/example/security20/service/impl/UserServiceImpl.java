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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserPhysicalParametersRepository physicalParametersRepository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User userToSave = mapper.convertToEntity(userDTO);
        userToSave.setPassword(encoder.encode(userToSave.getPassword()));
        User savedUser = userRepository.save(userToSave);
        System.out.println(savedUser);
        return mapper.convertToDto(savedUser);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveUserPhysicalParameters(UserPhysicalParameters userPhysicalParameters) {
        physicalParametersRepository.save(userPhysicalParameters);
    }

}
