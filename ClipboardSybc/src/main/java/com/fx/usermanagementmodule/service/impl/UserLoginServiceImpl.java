package com.fx.usermanagementmodule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fx.usermanagementmodule.domain.ResponseResult;
import com.fx.usermanagementmodule.domain.entity.LoginUser;
import com.fx.usermanagementmodule.domain.entity.User;
import com.fx.usermanagementmodule.domain.vo.LoginUserVo;
import com.fx.usermanagementmodule.domain.vo.UserVo;
import com.fx.usermanagementmodule.enums.AppHttpCodeEnum;
import com.fx.usermanagementmodule.exception.SystemException;
import com.fx.usermanagementmodule.utils.JwtUtil;
import com.fx.usermanagementmodule.utils.RedisCache;
import com.fx.usermanagementmodule.mapper.UserMapper;
import com.fx.usermanagementmodule.service.UserLoginService;
import com.fx.usermanagementmodule.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2022-02-10 18:19:39
 */
@Service("userLoginService")
public class UserLoginServiceImpl extends ServiceImpl<UserMapper, User> implements UserLoginService
{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user)
    {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (Objects.isNull(authenticationToken))
        {
            throw new RuntimeException("用户名或密码错误");
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        redisCache.setCacheObject("login:" + userId, loginUser);

        UserVo userVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserVo.class);
        LoginUserVo loginUserVo = new LoginUserVo(jwt, userVo);
        return ResponseResult.okResult(loginUserVo);
    }

    @Override
    public ResponseResult register(User user)
    {
        //如果用户名已经存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,user.getUserName());
        if(userMapper.exists(queryWrapper))
        {
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        save(user);
        return ResponseResult.okResult("注册成功");
    }

    @Override
    public ResponseResult logout()
    {
        // 就是删除redis中的用户信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + userId);
        return ResponseResult.okResult();
    }
}