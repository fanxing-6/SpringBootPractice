package com.fx.controller;

import com.fx.domain.ResponseResult;
import com.fx.domain.entity.User;
import com.fx.enums.AppHttpCodeEnum;
import com.fx.exception.SystemException;
import com.fx.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController
{
    @Autowired
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user)
    {
        if(!StringUtils.hasText(user.getUserName()))
        {
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return blogLoginService.login(user);
    }

    @PostMapping("/logout")
    public  ResponseResult logout()
    {
        return blogLoginService.logout();
    }

}
