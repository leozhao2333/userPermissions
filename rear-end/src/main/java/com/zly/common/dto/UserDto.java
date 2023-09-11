package com.zly.common.dto;

import lombok.Data;

@Data
public class UserDto {

    private String id;

    /**
     * 所属机构id
     */
    private String groupId;

    /**
     * 登录名称
     */
    private String loginName;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPass;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 是否是管理，0否1是
     */
    private String ifManage;
}
