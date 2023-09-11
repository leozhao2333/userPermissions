package com.zly.common.dto;

import lombok.Data;

@Data
public class FileDto{

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

    /**
     * 创建时间
     */
    private String createTime;

    //查询人
    private String selecter;

}
