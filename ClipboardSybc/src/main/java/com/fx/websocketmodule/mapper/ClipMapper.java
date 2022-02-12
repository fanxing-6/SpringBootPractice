package com.fx.websocketmodule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fx.websocketmodule.domain.entity.Clip;
import org.apache.ibatis.annotations.Mapper;


/**
 * (Clip)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-12 19:07:44
 */
@Mapper
public interface ClipMapper extends BaseMapper<Clip> {

}

