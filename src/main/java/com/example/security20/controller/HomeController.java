package com.example.security20.controller;

import com.example.security20.dto.CustomUserDetails;
import com.example.security20.dto.UserDTO;
import com.example.security20.entity.User;
import com.example.security20.entity.UserPhysicalParameters;
import com.example.security20.repository.UserPhysicalParametersRepository;
import com.example.security20.repository.UserRepository;
import com.example.security20.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserPhysicalParametersRepository userPhysicalParametersRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home")
    public String getUserProfile(Authentication authentication, Model model) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        List<UserPhysicalParameters> userPhysicalParametersList = userPhysicalParametersRepository.findPhysicalParametersByUserId(userId);
        model.addAttribute("userPhysicalParametersList", userPhysicalParametersList);
        model.addAttribute("userId", userId);
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("firstName", userDetails.getFirstName());
        model.addAttribute("lastName", userDetails.getLastName());

        List<User> usersList = userService.findAllUsers();
        model.addAttribute("usersList", usersList);

        return "mainPage";
    }
    @GetMapping("/createPhysicalParameters")
    public String createPhysicalParameters(Model model){
        model.addAttribute("userPhysicalParameters",new UserPhysicalParameters());
        return "formPhys";
    }
    @GetMapping("/create")
    public String userCreate(Model model){
        model.addAttribute("user",new UserDTO());
        return "signup";
    }
    @PostMapping("/create")
    public String userSave(@ModelAttribute("user") User user, Model model){
        userService.createUser(user);
        List<User> usersList = userService.findAllUsers();
        model.addAttribute("usersList", usersList);
        return "mainPage";
    }
    @PostMapping("/createPhysicalParameters")
    public String createPhysicalParameters(Authentication authentication, @ModelAttribute("userPhysicalParameters") UserPhysicalParameters userPhysicalParameters, Model model) {
        User user = userRepository.findByUserName(authentication.getName());
        Long userId = user.getId();
        if (user == null) {
            // Обработка случая, когда пользователь не найден
            System.out.println("Обработка случая, когда пользователь не найден");
            return "errorPage";
        }

        userPhysicalParameters.setUserId(userId);

        try {
            String date = userPhysicalParameters.getDate();
            userPhysicalParameters.setDate(date);
            userService.saveUserPhysicalParameters(userPhysicalParameters);
            // Возврат результата или представления с информацией о сохраненных параметрах
            return "redirect:/api/v1/home";
        } catch (DataIntegrityViolationException e) {
            // Обработка исключения при сохранении в базу данных
            System.out.println("Обработка исключения при сохранении в базу данных");
            return "errorPage";
        }
    }
}