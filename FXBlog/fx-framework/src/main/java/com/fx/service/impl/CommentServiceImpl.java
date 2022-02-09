package com.fx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fx.domain.entity.Comment;
import com.fx.mapper.CommentMapper;
import com.fx.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author makejava
 * @since 2022-02-10 00:08:03
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService
{

}

