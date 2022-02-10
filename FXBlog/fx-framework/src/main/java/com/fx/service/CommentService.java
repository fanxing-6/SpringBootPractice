package com.fx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fx.domain.ResponseResult;
import com.fx.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2022-02-10 00:08:03
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}

