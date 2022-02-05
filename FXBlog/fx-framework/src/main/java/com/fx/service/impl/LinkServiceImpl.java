package com.fx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fx.constants.SystemConstants;
import com.fx.domain.ResponseResult;
import com.fx.domain.entity.Link;
import com.fx.domain.vo.LinkVo;
import com.fx.mapper.LinkMapper;
import com.fx.service.LinkService;
import com.fx.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2022-02-05 00:28:03
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService
{

    @Override
    public ResponseResult getAllLink()
    {
        //查询所有审核通过友链
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORML);

        List<Link> links = list(queryWrapper);
        List<LinkVo> vs = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        return ResponseResult.okResult(vs);
    }
}

