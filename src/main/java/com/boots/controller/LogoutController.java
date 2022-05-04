package com.boots.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {

    @GetMapping(value = "/logoutSuccessUser")
    //@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User logout")
    public @ResponseBody String logoutUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            request.getSession().invalidate();
//        }

        System.out.println("Пользователь разлогинен");

        return "Пользователь разлогинен";
    }

    @GetMapping(value = "/loginSuccessUser")
    //@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User login")
    public @ResponseBody String loginUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            request.getSession().invalidate();
//        }

        System.out.println("Пользователь залогинен");
        return "Пользователь залогинен";
    }

    @GetMapping(value = "/login")
    //@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User logout")
    public @ResponseBody String loginError() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null) {
//            request.getSession().invalidate();
//        }

        System.out.println("Пользователь не залогинен");

        return "Пользователь не залогинен";
    }
}
