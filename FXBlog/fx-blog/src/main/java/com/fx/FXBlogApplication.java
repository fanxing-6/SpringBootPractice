package com.fx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fx.mapper")
public class FXBlogApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(FXBlogApplication.class, args);
    }
}
