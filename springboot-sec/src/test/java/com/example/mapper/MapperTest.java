package com.example.mapper;

import com.example.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
public class MapperTest
{
    @Autowired
    private UserMapper userMapper;

    @Test
    public void TestBCryptPasswordEncoder()
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("=============================================================");
        String encode1 = passwordEncoder.encode("12");
//        String encode2 = passwordEncoder.encode("1234");
        System.out.println(encode1);
    }


    @Test
    public void testUserMapper()
    {
        List<User> users = userMapper.selectList(null);
        System.out.println("============================================================================");
        System.out.println(users);
        System.out.println("=========================================================================");
    }
}
