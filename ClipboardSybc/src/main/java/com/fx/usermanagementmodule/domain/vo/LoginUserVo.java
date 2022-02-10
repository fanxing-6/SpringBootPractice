package com.fx.usermanagementmodule.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserVo
{
    private String token;
    private UserVo userVo;

}
