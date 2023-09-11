package com.zly.common.vo;

import lombok.Data;


@Data
public class UserGroupVo {


    private String id;

    /**
     * 所属机构id
     */
    private String oId;

    /**
     * 分组名称
     */
    private String groupName;

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
