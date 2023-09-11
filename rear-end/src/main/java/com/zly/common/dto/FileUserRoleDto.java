package com.zly.common.dto;

import lombok.Data;

@Data
public class FileUserRoleDto {


    private String id;

    /**
     * 文件
     */
    private String fileId;

    /**
     * 用户
     */
    private String userId;

    /**
     * 读写权限，0读1不读
     */
    private String roleRead;

    /**
     * 管理权限，0管理1不管理
     */
    private String roleManage;
}
