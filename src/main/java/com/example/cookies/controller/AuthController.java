package com.example.cookies.controller;

import com.example.cookies.model.User;
import com.example.cookies.service.JwtService;
import com.example.cookies.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(
            JwtService jwtService,
            UserService userService
    ) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    // REGISTER
    @PostMapping("/register")
    public String register(
            @RequestBody User user
    ) {

        return userService.register(user);
    }

    // LOGIN
    @PostMapping("/login")
    public String login(
            @RequestBody User user,
            HttpServletResponse response
    ) {

        boolean loginOk =
                userService.login(
                        user.getUsername(),
                        user.getPassword()
                );

        if (!loginOk) {
            return "Usuario o contraseña incorrectos";
        }

        String token =
                jwtService.generarToken(
                        user.getUsername()
                );

        Cookie cookie =
                new Cookie("jwt", token);

        cookie.setHttpOnly(true);

        cookie.setSecure(false);

        cookie.setPath("/");

        cookie.setMaxAge(3600);

        response.addCookie(cookie);

        return "Login exitoso";
    }

    // PROFILE
    @GetMapping("/profile")
    public String profile(
            HttpServletRequest request
    ) {

        Cookie[] cookies =
                request.getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if (
                        cookie.getName()
                                .equals("jwt")
                ) {

                    String token =
                            cookie.getValue();

                    String usuario =
                            jwtService
                                    .extraerUsuario(token);

                    return "Bienvenida "
                            + usuario;
                }
            }
        }

        return "No hay sesión";
    }

    // LOGOUT
    @GetMapping("/logout")
    public String logout(
            HttpServletResponse response
    ) {

        Cookie cookie =
                new Cookie("jwt", null);

        cookie.setHttpOnly(true);

        cookie.setSecure(false);

        cookie.setPath("/");

        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return "Logout exitoso";
    }
}