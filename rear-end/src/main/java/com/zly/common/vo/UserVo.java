package com.zly.common.vo;

import lombok.Data;

@Data
public class UserVo {

    private String id;

    //所属机构id
    private String oid;

    //登录名称
    private String loginName;

    //用户姓名
    private String userName;

    //用户密码
    private String userPass;

    //电话号码
    private String phone;

    //电子邮箱
    private String email;

    //创建人
    private String createBy;

    //创建时间
    private String createTime;

    //是否是管理，0否1是
    private String ifManage;
}
