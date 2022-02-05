package com.fx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fx.domain.ResponseResult;
import com.fx.domain.entity.Article;

public interface ArticleService extends IService<Article>
{

    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);
}
