package com.fx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fx.constants.SystemConstants;
import com.fx.domain.ResponseResult;
import com.fx.domain.entity.Article;
import com.fx.domain.entity.Category;
import com.fx.domain.vo.CategoryVo;
import com.fx.mapper.CategoryMapper;
import com.fx.service.ArticleService;
import com.fx.service.CategoryService;
import com.fx.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2022-02-04 20:54:17
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService
{
    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList()
    {
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleLambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleLambdaQueryWrapper);

        Set<Long> categoryIds = articleList.stream().map(article -> article.getCategoryId()).collect(Collectors.toSet());

        //查询文章表 已发布 获取文章的分类ID
        //查询分类表
        List<Category> categories = listByIds(categoryIds);

        categories = categories.stream().filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus())).collect(Collectors.toList());

        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);

    }
}

