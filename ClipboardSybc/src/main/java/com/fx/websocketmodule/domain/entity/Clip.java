package com.fx.websocketmodule.domain.entity;

import java.util.Date;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (Clip)表实体类
 *
 * @author makejava
 * @since 2022-02-12 19:05:21
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("fx_clip")
public class Clip
{
    //剪切板内容ID
    @TableId
    private Long id;

    //剪切板内容,非文本数据上传到obs
    private String context;

    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

    private Long userId;
    //来源设备的ID,用于真正区分设备
    private String deviceId;
    //用户名
    private String userName;
    //数据来源设备名称

    private String deviceName;
    //对象存储链接
    private String obsLink;
    //0 没有上传到云存储 1 上传到云存储
    private Integer ossFlag;
    //0文本 1 图片 2 URL 3 文件 4 txt 5 gif
    private String contentType;
    //0 代表为收藏 1 代表收藏
    private Integer favoriteFlag;
    //0:不想同步,1:想要同步
    private Integer syncedFlag;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
