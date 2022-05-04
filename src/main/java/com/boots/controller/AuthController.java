package com.boots.controller;

import com.boots.config.jwt.JwtUtils;
import com.boots.domain.User;

import com.boots.dto.AuthRequest;
import com.boots.dto.AuthResponse;
import com.boots.dto.JwtResponse;
//import com.boots.service.UserDetailsImpl;
import com.boots.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private JwtProvider jwtProvider;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * The method that generates the JWT token
     * @param //request
     * @return AuthResponse(token) - JSON
     * @author dfcz652
     */
    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

//    @PostMapping("/auth")
//    public AuthResponse auth(@RequestParam("username") String username, @RequestParam("password") String password) {
//        User user = userService.PasswordEncryption(request.getUsername(), request.getPassword());
//        String token = jwtProvider.generateToken(user.getUsername());
//        System.out.println(new AuthResponse(token));
//        return new AuthResponse(token);
//    }
}
