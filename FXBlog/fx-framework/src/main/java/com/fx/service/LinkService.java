package com.fx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fx.domain.ResponseResult;
import com.fx.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-02-05 00:28:03
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}

