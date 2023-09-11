package com.zly.common.vo;

import lombok.Data;

@Data
public class FgUgRoleVo {

    private String id;

    /**
     * 文件组
     */
    private String fgId;
    private String fgIdName;

    /**
     * 用户组
     */
    private String ugId;
    private String ugIdName;

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
