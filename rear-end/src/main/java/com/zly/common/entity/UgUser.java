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
 * @since 2023-04-26
 */
@Data
@TableName("ug_user")
public class UgUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 用户组id
     */
    @TableField("ugid")
    private String ugid;

    /**
     * 用户id
     */
    @TableField("uid")
    private String uid;
}
