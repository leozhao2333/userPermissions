package com.zly.common.enums;

import lombok.Getter;


@Getter
public enum ErrorCode {

    OK(200, "请求成功"),
    INVALID_ARG(400, "请求失败"),
    TIMEOUT(408,"请求超时"),
    LOGIN_NOT_FOUND(501,"用户名或密码错误"),
    LOGIN_EXIT(502,"用户未登录"),
    LOGIN_ERROR_USERNAME(506,"用户名不存在"),
    LOGIN_AGAIN(504,"请重新登录"),
    LOGIN_OUT(200,"登出成功"),
    ERROR_PASSWORD_IS_NULL(600,"密码不能为空"),
    NULL_PARAM(603,"请求参数不能为空"),
    SAME_LOGIN_NAME(701,"用户名已存在"),
    SAME_USER_GROUP_NAME(702,"用户组名已存在"),
    SAME_FILE_GROUP_NAME(703,"文件组名已存在"),
    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
