package com.zly.common.dto;

import lombok.Data;

@Data
public class OrganizationDto {

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
}
