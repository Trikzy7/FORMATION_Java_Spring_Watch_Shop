package com.example.tp_cbtw_java_watch_shop.security;

import com.example.tp_cbtw_java_watch_shop.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    private final JwtService jwtService;
    private final UserService userService;

    public SecurityUtils(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public void checkAdmin(String authHeader) {
        String token = authHeader.substring(7); // enlever "Bearer "
        String username = jwtService.extractEmail(token);
        String role = userService.getUserRoleByUsername(username);
        if (!"ADMIN".equals(role)) {
            throw new RuntimeException("Accès refusé : vous devez être administrateur");
        }
    }

}
