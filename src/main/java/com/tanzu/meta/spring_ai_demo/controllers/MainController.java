package com.tanzu.meta.spring_ai_demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    // Add this method to ChatController.java
    @GetMapping("/")
    public String landingPage() {
        return "landing";
    }
}
