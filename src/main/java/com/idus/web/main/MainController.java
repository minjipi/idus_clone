package com.idus.web.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping("/")
    public String main(){
        return "main.html";
    }

    @GetMapping("/test")
    public String test(){
        return "test.html";
    }
}
