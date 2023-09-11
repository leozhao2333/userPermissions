package com.zly.common.dto;

import lombok.Data;

@Data
public class UgUserDto {

    private String id;

    /**
     * 用户组id
     */
    private String ugid;

    /**
     * 用户id
     */
    private String uid;
}
