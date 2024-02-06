package com.example.security20.controller;

import com.example.security20.dto.CustomUserDetails;
import com.example.security20.dto.UserDTO;
import com.example.security20.entity.User;
import com.example.security20.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
//    @GetMapping("/home")
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    public String getHomePage(@PathVariable Long id, Model model){
////        Optional<User> user= userService.findById(id);
////        model.addAttribute("user", user);
//        return "testPage";
//    }
//    @GetMapping("/home")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public String getAdminPage(){
//        return "admin";
//    }
    //    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    public ResponseEntity<String> getHomePage(){
//        return ResponseEntity.ok().body(
//                "welcome to Api End point"
//        );
//    }

//    @GetMapping()
//    @PreAuthorize("hasAuthority('ROLE_USER')")
//    public List<UserDTO> getAdmin(){
//        return userService.findAllUsers();
//    }
}