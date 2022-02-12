package com.fx.websocketmodule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fx.websocketmodule.domain.entity.Clip;
import com.fx.websocketmodule.mapper.ClipMapper;
import com.fx.websocketmodule.service.ClipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * (Clip)表服务实现类
 *
 * @author makejava
 * @since 2022-02-12 19:08:32
 */
@Service("clipService")
public class ClipServiceImpl extends ServiceImpl<ClipMapper, Clip> implements ClipService
{
    @Autowired
    private ClipMapper clipMapper;

    @Override
    public List<Clip> getFavorite()
    {
        List<Clip> clips = new ArrayList<>();
        LambdaQueryWrapper<Clip> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Clip::getFavoriteFlag, 1);
        clips = clipMapper.selectList(queryWrapper);
        return clips;
    }
}

