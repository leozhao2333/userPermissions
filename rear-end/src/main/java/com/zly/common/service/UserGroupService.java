package com.zly.common.service;

import com.zly.common.dto.UserGroupDto;
import com.zly.common.entity.UserGroup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zly.common.vo.UserGroupVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zly
 * @since 2023-04-23
 */
public interface UserGroupService extends IService<UserGroup> {

    /**
     * 根据用户id和文件组名拿取权限
     * @param userId
     * @param fgId
     * @return
     */
    UserGroupVo getUserGroupFileRole(String userId,String fgId);

    /**
     * 获取用户组列表
     * @param userGroupDto
     * @param request
     * @return
     */
    List<UserGroupVo> getList(UserGroupDto userGroupDto, HttpServletRequest request);

    /**
     * 编辑用户组
     * @param userGroupDto
     * @param request
     */
    void editUserGroup(UserGroupDto userGroupDto, HttpServletRequest request);

    /**
     *  删除用户组
     * @param userGroupDto
     * @param request
     */
    void deleteUserGroup(UserGroupDto userGroupDto, HttpServletRequest request);

}
