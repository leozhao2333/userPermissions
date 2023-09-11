package com.zly.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author zly
 * @since 2023-04-23
 */
@Data
@TableName("fg_ug_role")
public class FgUgRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 文件组
     */
    @TableField("fg_id")
    private String fgId;

    /**
     * 用户组
     */
    @TableField("ug_id")
    private String ugId;

    /**
     * 读写权限，0读1不读
     */
    @TableField("role_read")
    private String roleRead;

    /**
     * 管理权限，0管理1不管理
     */
    @TableField("role_manage")
    private String roleManage;

}
