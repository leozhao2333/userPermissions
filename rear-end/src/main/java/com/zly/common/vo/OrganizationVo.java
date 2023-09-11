package com.zly.common.vo;

import lombok.Data;

@Data
public class OrganizationVo {

    private String id;

    private String organizationName;

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
