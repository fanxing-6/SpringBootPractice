package com.example.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
    @RequestMapping("user/hello")
    @PreAuthorize("hasAuthority('test')")//表示权限
    public String getHello()
    {
        return "hello";
    }
}
