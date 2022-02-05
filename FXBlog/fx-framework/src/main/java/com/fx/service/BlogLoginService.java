package com.fx.service;

import com.fx.domain.ResponseResult;
import com.fx.domain.entity.User;

public interface BlogLoginService
{

    ResponseResult login(User user);

    ResponseResult logout();
}
