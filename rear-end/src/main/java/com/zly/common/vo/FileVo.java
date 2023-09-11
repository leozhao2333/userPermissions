package com.zly.common.vo;

import lombok.Data;

@Data
public class FileVo {

    private String id;

    /**
     * 所属机构
     */
    private String oid;

    /**
     * 所属文件组id
     */
    private String fgId;

    /**
     * 唯一标识
     */
    private String guid;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 创建人
     */
    private String createBy;
    private String createByName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 读写权限，0读1不读
     */
    private String roleRead;

    /**
     * 管理权限，0管理1不管理
     */
    private String roleManage;
}
