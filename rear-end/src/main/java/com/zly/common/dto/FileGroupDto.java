package com.zly.common.dto;

import lombok.Data;

@Data
public class FileGroupDto {


    private String id;

    /**
     * 所属机构
     */
    private String oid;

    /**
     * 文件组名
     */
    private String fileGroupName;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private String createTime;

    private String selecter;
}
