package com.fx.usermanagementmodule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fx.usermanagementmodule.domain.ResponseResult;
import com.fx.usermanagementmodule.domain.entity.User;


/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2022-02-10 18:19:39
 */
public interface UserLoginService extends IService<User> {

    ResponseResult login(User user);

    ResponseResult register(User user);

    ResponseResult logout();
}

