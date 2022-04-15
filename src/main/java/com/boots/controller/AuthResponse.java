package com.boots.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Issuing a JWT token to the user
 */
@Data
@AllArgsConstructor
public class AuthResponse {

    private String token;
}
