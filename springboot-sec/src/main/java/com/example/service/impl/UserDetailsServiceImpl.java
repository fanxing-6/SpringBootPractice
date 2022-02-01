package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.domain.LoginUser;
import com.example.domain.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Service
public class UserDetailsServiceImpl implements UserDetailsService
{


    //TODO:查询权限信息

    @Autowired
    private UserMapper userMapper;

    //查询用户
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName, username);
        User user = userMapper.selectOne(queryWrapper);
        if (Objects.isNull(user))
        {
            throw new RuntimeException("用户名或密码错误!");
        }
        List<String> list = new ArrayList<>(Arrays.asList("test", "admin"));
        return new LoginUser(user, list);
    }
}
