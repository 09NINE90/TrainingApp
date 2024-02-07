package com.example.security20.controller;

import com.example.security20.dto.CustomUserDetails;
import com.example.security20.dto.UserPhysicalParametersDTO;
import com.example.security20.entity.UserPhysicalParameters;
import com.example.security20.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1")
public class HomeController {
    @Autowired
    private UserService userService;

    @GetMapping("/home")
    public String getUserProfile(Authentication authentication, Model model) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();
        model.addAttribute("userId", userId);
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("firstName", userDetails.getFirstName());
        model.addAttribute("lastName", userDetails.getLastName());

        return "testPage";
    }
    @GetMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String updateUserParameters(Model model){
        UserPhysicalParametersDTO physicalParameters = new UserPhysicalParametersDTO();
        model.addAttribute("physicalParameters", physicalParameters);
        return "testPage";
    }
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String updateUserParameters(@ModelAttribute("physicalParameters") UserPhysicalParametersDTO physicalParametersDTO, BindingResult result, Model model){
        userService.updatePhysicalParameters(physicalParametersDTO);
        return "testPage";
    }

}