package com.idus.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/signup")
    public void signup(){

    }

    @GetMapping("email_signup")
    public void email_signup(){

    }
}
