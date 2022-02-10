package com.fx.usermanagementmodule.controller;


import com.fx.usermanagementmodule.domain.ResponseResult;
import com.fx.usermanagementmodule.domain.entity.User;
import com.fx.usermanagementmodule.enums.AppHttpCodeEnum;
import com.fx.usermanagementmodule.exception.SystemException;
import com.fx.usermanagementmodule.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController
{
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user)
    {
        if ((!StringUtils.hasText(user.getUserName())) || (!StringUtils.hasText(user.getPassword())))
        {
            throw new SystemException(AppHttpCodeEnum.NULL_USERNAME_OR_PASSWORD);
        }

        return userLoginService.login(user);
    }
    @PostMapping("/logout")
    public  ResponseResult logout()
    {
        return userLoginService.logout();
    }


    @PostMapping("/register")
    public ResponseResult register(@RequestBody User user)
    {
        if ((!StringUtils.hasText(user.getUserName())) || (!StringUtils.hasText(user.getPassword())))
        {
            throw new SystemException(AppHttpCodeEnum.NULL_USERNAME_OR_PASSWORD);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userLoginService.register(user);

    }

}
