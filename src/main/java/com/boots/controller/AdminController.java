package com.boots.controller;

import com.boots.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    /**
     * Show all registered users<associated with userService.allUsers()>
     * @param model
     * @return admin
     */
    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    /**
     * Method for deleting a user<associated with userService.deleteUser(userId)>
     * @param userId
     * @param action
     * @param model
     * @return redirect:/admin
     */
    @PostMapping("/admin")
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    /**
     * Method for getting a view of all registered users<associated with userService.usergetList(userId)>
     * @param userId
     * @param model
     * @return admin
     */
    @GetMapping("/admin/get/{userId}")
    public String getUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("allUsers", userService.usergetList(userId));
        return "admin";
    }
}
