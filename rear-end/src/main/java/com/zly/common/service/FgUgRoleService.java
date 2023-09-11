package com.zly.common.service;

import com.zly.common.dto.FgUgRoleDto;
import com.zly.common.entity.FgUgRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zly.common.vo.FgUgRoleVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zly
 * @since 2023-04-23
 */
public interface FgUgRoleService extends IService<FgUgRole> {

    /**
     * 根据文件组获取用户组权限
     * @param fgUgRoleDto
     * @return
     */
    List<FgUgRoleVo> getUgRoleByFg(FgUgRoleDto fgUgRoleDto);

    /**
     * 编辑文件组用户组权限
     * @param fgUgRoleDto
     */
    void editFgUgRole(FgUgRoleDto fgUgRoleDto);

    /**
     * 删除文件组用户组权限
     * @param fgUgRoleDto
     */
    void deleteFgUgRole(FgUgRoleDto fgUgRoleDto);

}
