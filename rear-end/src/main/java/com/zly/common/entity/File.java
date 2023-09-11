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
 * @since 2023-02-17
 */
@Data
@TableName("file")
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 所属机构
     */
    @TableField("oid")
    private String oid;

    /**
     * 所属文件组id
     */
    @TableField("fg_id")
    private String fgId;

    /**
     * 唯一标识
     */
    @TableField("GUID")
    private String guid;

    /**
     * 文件名
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 文件类型
     */
    @TableField("file_type")
    private String fileType;

    /**
     * 创建人
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private String createTime;

    /**
     * 是否删除，0否1是
     */
    @TableField("del_flag")
    private Integer delFlag;
}
