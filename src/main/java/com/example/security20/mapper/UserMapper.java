package com.example.security20.mapper;

import com.example.security20.dto.UserDTO;
import com.example.security20.dto.UserPhysicalParametersDTO;
import com.example.security20.entity.User;
import com.example.security20.entity.UserPhysicalParameters;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public User convertToEntity(UserDTO userDTO){
        return modelMapper.map(userDTO, User.class);
    }
    public UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    public UserPhysicalParameters convertToEntity(UserPhysicalParametersDTO userPhysicalParametersDTO){return modelMapper.map(userPhysicalParametersDTO, UserPhysicalParameters.class);}
    public UserPhysicalParametersDTO convertToDto(UserPhysicalParameters userPhysicalParameters){return modelMapper.map(userPhysicalParameters, UserPhysicalParametersDTO.class);}
}