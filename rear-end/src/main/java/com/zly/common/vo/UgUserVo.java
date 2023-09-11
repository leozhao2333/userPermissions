package com.zly.common.vo;

import lombok.Data;

@Data
public class UgUserVo {

    private String id;

    /**
     * 用户组id
     */
    private String ugid;
    private String ugidName;

    /**
     * 用户id
     */
    private String uid;
    private String uidName;
}
