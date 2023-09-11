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
@TableName("file_user_role")
public class FileUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 文件
     */
    @TableField("file_id")
    private String fileId;

    /**
     * 用户
     */
    @TableField("user_id")
    private String userId;

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
