package com.zly.common.dto;

import lombok.Data;

@Data
public class LoginDto {

    //登录名称
    private String loginName;

    //用户密码
    private String userPass;
}
