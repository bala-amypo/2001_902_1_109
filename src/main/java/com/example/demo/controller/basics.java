package com.example.demo.controller;
import org.springframework.web.bind.annotation.GetMapping;
import.org.springframework.web.bind.annotation.RestController;
@RestController
public class basics{
    @GetMapping("/home")
    public String sample(){
        return "hello world";
    }
}
