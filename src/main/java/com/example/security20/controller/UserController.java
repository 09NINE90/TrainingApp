package com.example.security20.controller;

import com.example.security20.dto.UserDTO;
import com.example.security20.entity.User;
import com.example.security20.entity.UserPhysicalParameters;
import com.example.security20.repository.UserPhysicalParametersRepository;
import com.example.security20.repository.UserRepository;
import com.example.security20.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    private UserPhysicalParametersRepository userPhysicalParametersRepository;
    @GetMapping("/{userId}")
    public String userCreate(@PathVariable("userId") Long id, Model model){
        Optional<User> userOptional = userService.getUserById(id);
        User user = userOptional.get();
        List<UserPhysicalParameters> userPhysicalParametersList = userPhysicalParametersRepository.findPhysicalParametersByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("userPhysicalParametersList", userPhysicalParametersList);
        return "userPage";
    }

}

