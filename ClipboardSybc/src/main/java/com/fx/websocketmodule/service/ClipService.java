package com.fx.websocketmodule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fx.websocketmodule.domain.entity.Clip;

import java.util.List;


/**
 * (Clip)表服务接口
 *
 * @author makejava
 * @since 2022-02-12 19:08:31
 */
public interface ClipService extends IService<Clip>
{
    List<Clip> getFavorite();
}

