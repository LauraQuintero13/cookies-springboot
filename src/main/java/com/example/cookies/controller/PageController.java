package com.example.cookies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String inicio() {
        return "login";
    }

    @GetMapping("/login-page")
    public String login() {
        return "login";
    }

    @GetMapping("/register-page")
    public String register() {
        return "register";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}