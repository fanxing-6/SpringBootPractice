package com.fx.usermanagementmodule.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo
{
    //主键@TableId
//    private Long id;

    //用户名
    private String userName;

    //用户类型：0代表普通用户，1代表管理员
    private String type;

    //邮箱
    private String email;
    //手机号
    private String phonenumber;
    //用户性别（0男，1女，2未知）
    private String sex;



}
