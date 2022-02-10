package com.fx.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordEncoderTest
{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test()
    {
        // $2a$10$w0hsTIYzi9MY.JonxwLgBeJgUy5YyXiZeG9aChe6kSieZhNlLPzMS
        String passwd = "$2a$10$w0hsTIYzi9MY.JonxwLgBeJgUy5YyXiZeG9aChe6kSieZhNlLPzMS";
        System.out.println("===========================================================");
        System.out.println(passwordEncoder.matches("1234",passwd));
    }

}
