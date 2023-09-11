package com.zly.common.dto;

import lombok.Data;

@Data
public class FgUgRoleDto {

    private String id;

    /**
     * 文件组
     */
    private String fgId;

    /**
     * 用户组
     */
    private String ugId;

    /**
     * 读写权限，0读1不读
     */
    private String roleRead;

    /**
     * 管理权限，0管理1不管理
     */
    private String roleManage;

}
