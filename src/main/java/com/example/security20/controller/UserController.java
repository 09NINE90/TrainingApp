package com.example.security20.controller;

import com.example.security20.dto.CustomUserDetails;
import com.example.security20.dto.UserDTO;
import com.example.security20.entity.*;
import com.example.security20.repository.UserPhysicalParametersRepository;
import com.example.security20.service.MailService;
import com.example.security20.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    private UserPhysicalParametersRepository userPhysicalParametersRepository;
    @Autowired
    MailService mailService;

    @GetMapping("/logout")
    public String logout() {

        return "login";
    }
    @GetMapping("/{userId}")
    public String userCreate(@PathVariable("userId") Long id, Model model){
        Optional<User> userOptional = userService.getUserById(id);
        User user = userOptional.get();
        List<UserPhysicalParameters> userPhysicalParametersList = userPhysicalParametersRepository.findPhysicalParametersByUserId(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("userPhysicalParametersList", userPhysicalParametersList);
        return "userPage";
    }
    @GetMapping("/delete/{userId}")
    public String userDelete(@PathVariable("userId") Long id){
        userService.deleteUserPhysicalParameters(id);
        userService.deleteUser(id);
        return "redirect:/api/v1/home";
    }
    @GetMapping("/create")
    public String userCreate(Model model){
        model.addAttribute("user",new UserDTO());
        return "signup";
    }
    @PostMapping("/create")
    public String userSave(Authentication authentication, @ModelAttribute("user") User user, Model model){

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if (userDetails.getRole().equals("ROLE_COACH")){
            user.setTrainerId(userDetails.getId());
            user.setRoles("ROLE_USER");
        }else if (userDetails.getRole().equals("ROLE_ADMIN")){
            user.setTrainerId(userDetails.getId());
            user.setRoles("ROLE_COACH");
        }
//        user.setRoles("ROLE_ADMIN");
        mailService.sendMail(user, MailType.REGISTRATION, new Properties());
        userService.createUser(user);
        return "redirect:/api/v1/home";
    }

}

