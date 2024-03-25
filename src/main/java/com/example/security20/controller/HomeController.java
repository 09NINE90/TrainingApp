package com.example.security20.controller;

import com.example.security20.dto.CustomUserDetails;
import com.example.security20.dto.UserDTO;
import com.example.security20.entity.User;
import com.example.security20.entity.UserPhysicalParameters;
import com.example.security20.entity.WorkoutPlan;
import com.example.security20.repository.UserPhysicalParametersRepository;
import com.example.security20.repository.UserRepository;
import com.example.security20.repository.WorkoutPlanRepository;
import com.example.security20.service.UserService;

import com.example.security20.service.WorkoutPlanService;
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
    WorkoutPlanService workoutPlanService;
    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;

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
        List<User> usersList = null;
        if (userDetails.getRole().equals("ROLE_COACH")){
            usersList = userService.getUserByRole("ROLE_USER");
        }else if (userDetails.getRole().equals("ROLE_ADMIN")){
            usersList = userService.getUserByRole("ROLE_COACH");
        }
        model.addAttribute("usersList", usersList);
        return "mainPage";
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

}