package com.example.controller;


import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
    @RequestMapping("user/hello")
    public String getHello()
    {
        return "hello";
    }
}
