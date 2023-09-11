package com.zly.common.vo;

import lombok.Data;

@Data
public class FileUserRoleVo {


    private String id;

    /**
     * 文件
     */
    private String fileId;
    private String fileIdName;

    /**
     * 用户
     */
    private String userId;
    private String userIdName;

    /**
     * 读写权限，0读1不读
     */
    private String roleRead;
    private String roleReadName;

    /**
     * 管理权限，0管理1不管理
     */
    private String roleManage;
    private String roleManageName;
}
