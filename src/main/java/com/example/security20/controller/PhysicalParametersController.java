package com.example.security20.controller;

import com.example.security20.entity.User;
import com.example.security20.entity.UserPhysicalParameters;
import com.example.security20.repository.UserRepository;
import com.example.security20.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
@RequestMapping("/api/v1")
public class PhysicalParametersController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/createPhysicalParameters")
    public String createPhysicalParameters(Model model){
        model.addAttribute("userPhysicalParameters",new UserPhysicalParameters());
        return "formPhys";
    }
    @PostMapping("/createPhysicalParameters")
    public String createPhysicalParameters(Authentication authentication, @ModelAttribute("userPhysicalParameters") UserPhysicalParameters userPhysicalParameters) {
        User user = userRepository.findByUserName(authentication.getName());
        Long userId = user.getId();
        if (user == null) {
            // Обработка случая, когда пользователь не найден
            System.out.println("Обработка случая, когда пользователь не найден");
            return "errorPage";
        }

        userPhysicalParameters.setUserId(userId);

        try {
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
