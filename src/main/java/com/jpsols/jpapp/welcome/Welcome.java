package com.jpsols.jpapp.welcome;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {

    @GetMapping("/")
    public String Welcome() {
        return "Welcome";
    }
}
