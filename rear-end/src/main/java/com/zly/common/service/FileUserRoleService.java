package com.zly.common.service;

import com.zly.common.dto.FileUserRoleDto;
import com.zly.common.entity.FileUserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zly.common.vo.FileUserRoleVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zly
 * @since 2023-04-23
 */
public interface FileUserRoleService extends IService<FileUserRole> {

    /**
     * 编辑文件用户权限
     * @param fileUserRoleDto
     */
    void editFileUserRole(FileUserRoleDto fileUserRoleDto);

    /**
     * 删除文件用户权限
     * @param fileUserRoleDto
     */
    void deleteFileUserRole(FileUserRoleDto fileUserRoleDto);

    /**
     * 根据文件获取用户权限
     * @param fileUserRoleDto
     * @return
     */
    List<FileUserRoleVo> getUserRoleByFile(FileUserRoleDto fileUserRoleDto);
}
