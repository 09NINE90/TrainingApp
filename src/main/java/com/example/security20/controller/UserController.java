package com.example.security20.controller;

import com.example.security20.dto.UserDTO;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    private UserPhysicalParametersRepository userPhysicalParametersRepository;
    @GetMapping("/create")
    public String userCreate(Model model){
        model.addAttribute("user",new UserDTO());
        return "signup";
    }
    @PostMapping("/create")
    public String userSave(@ModelAttribute("user") UserDTO userDTO){
        userService.createUser(userDTO);
        return "redirect:/login";
    }

}

