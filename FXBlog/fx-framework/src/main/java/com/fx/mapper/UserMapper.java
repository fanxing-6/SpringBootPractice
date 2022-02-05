package com.fx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fx.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2022-02-05 14:39:47
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

}

