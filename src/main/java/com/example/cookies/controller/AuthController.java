package com.example.cookies.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/login")
    public String login(HttpServletResponse response) {

        Cookie cookie = new Cookie("usuario", "Laura");

        response.addCookie(cookie);

        return "Cookie creada";
    }

    @GetMapping("/perfil")
    public String perfil(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("usuario")) {

                    return "Bienvenida " + cookie.getValue();
                }
            }
        }

        return "No hay cookie";
    }
}