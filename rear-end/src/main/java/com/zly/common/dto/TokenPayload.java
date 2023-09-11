package com.zly.common.dto;

import lombok.Data;

@Data
public class TokenPayload {

    //用户id
    private String userId;
    //用户机构id
    private String oid;
    //用户是否是管理，0否1是
    private String ifManage;
    //签发时间
    private String iat;
    //生效时间
    private String nbf;
    //用户名
    private String userName;
}
