package com.boots.controller;

import lombok.Data;

/**
 * Method in which user data is stored to receive a JWT token
 */
@Data
public class AuthRequest {
    private String username;
    private String password;
}
