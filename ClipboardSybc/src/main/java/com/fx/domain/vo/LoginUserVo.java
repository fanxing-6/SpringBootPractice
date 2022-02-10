package com.fx.domain.vo;

import com.fx.domain.entity.User;
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
