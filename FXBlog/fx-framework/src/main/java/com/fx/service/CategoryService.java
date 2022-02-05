package com.fx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fx.domain.ResponseResult;
import com.fx.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-02-04 20:54:17
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

