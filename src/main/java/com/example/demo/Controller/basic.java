package com.example.demo.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class basic{
    @GetMapping("/home")
    public String sample(){
        return "hello world";
    }
}
