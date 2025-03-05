package com.example.xephangnguoidung.presentation.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LayoutController {
    @GetMapping("/layout")
    public String login() {
        return "auth/layout";
    }
}
