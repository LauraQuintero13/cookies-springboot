package com.example.cookies.service;

import com.example.cookies.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private final Map<String, String> users =
            new HashMap<>();

    public String register(User user) {

        if (users.containsKey(user.getUsername())) {
            return "El usuario ya existe";
        }

        users.put(
                user.getUsername(),
                user.getPassword()
        );

        return "Usuario registrado";
    }

    public boolean login(
            String username,
            String password
    ) {

        return users.containsKey(username)
                &&
                users.get(username)
                        .equals(password);
    }
}