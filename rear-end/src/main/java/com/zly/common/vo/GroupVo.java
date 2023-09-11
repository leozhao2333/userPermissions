package com.zly.common.vo;

import lombok.Data;

@Data
public class GroupVo {

    private String id;

    /**
     * 所属机构id
     */
    private String oid;

    /**
     * 分组名称
     */
    private String groupName;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private String updateTime;
}
