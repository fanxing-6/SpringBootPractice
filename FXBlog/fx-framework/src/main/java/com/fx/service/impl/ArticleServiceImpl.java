package com.fx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fx.constants.SystemConstants;
import com.fx.domain.ResponseResult;
import com.fx.domain.entity.Article;
import com.fx.domain.entity.Category;
import com.fx.domain.vo.ArticleDetailVo;
import com.fx.domain.vo.ArticleListVo;
import com.fx.domain.vo.HotArticleVo;
import com.fx.domain.vo.PageVo;
import com.fx.mapper.ArticleMapper;
import com.fx.service.ArticleService;

import com.fx.service.CategoryService;
import com.fx.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService
{
    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseResult hotArticleList()
    {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章 按照浏览量排序 最多十条
        //status == 0
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        queryWrapper.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page<>(1, 10);
        page(page, queryWrapper);

        // 直接返回articles会返回不必要字段 因此还需要VO处理
        List<Article> articles = page.getRecords();

        List<HotArticleVo> articleVos = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(articleVos);
    }

    /**
     * 分页查询
     *
     * @param pageNum    页数
     * @param pageSize   每页多少
     * @param categoryId 分类id 可不传
     * @return json相应
     */
    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId)
    {
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0, Article::getCategoryId, categoryId);

        lambdaQueryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);

        lambdaQueryWrapper.orderByDesc(Article::getIsTop);

        Page<Article> page = new Page<>(pageNum, pageSize);


        page(page, lambdaQueryWrapper);
        //查询分类名称
        List<Article> records = page.getRecords();
        for (Article article : records)
        {
            Category category = categoryService.getById(article.getCategoryId());
            article.setCategoryName(category.getName());
        }


        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);


        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id)
    {
        //根据 id 查询文章 转化成vo
        Article article = getById(id);
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if(category != null)
        {
            articleDetailVo.setCategoryName(category.getName());
        }
        return ResponseResult.okResult(articleDetailVo);
    }
}