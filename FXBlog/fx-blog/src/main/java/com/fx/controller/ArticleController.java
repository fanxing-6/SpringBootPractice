package com.fx.controller;


import com.fx.domain.ResponseResult;
import com.fx.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController
{
    @Autowired
    private ArticleService articleService;

//    @GetMapping("/list")
//    public List<Article> test()
//    {
//        return articleService.list();
//    }

    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList()
    {
        //查询热门文章 封装并返回
        ResponseResult result =  articleService.hotArticleList();
        return result;
    }

    //分页 / 分类
    @GetMapping("/articleList")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }
    
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id)
    {
        return articleService.getArticleDetail(id);
    }
}
