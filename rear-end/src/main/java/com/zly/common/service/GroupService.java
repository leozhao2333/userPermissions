package com.zly.common.service;

import com.zly.common.dto.GroupDto;
import com.zly.common.entity.Group;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zly.common.vo.GroupVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zly
 * @since 2023-02-17
 */
public interface GroupService extends IService<Group> {

    /**
     * 获取机构
     * @param groupDto
     * @param request
     * @return
     */
    List<GroupVo> getList(GroupDto groupDto, HttpServletRequest request);

    /**
     * 编辑机构
     * @param groupDto
     * @param request
     */
    void editGroup(GroupDto groupDto, HttpServletRequest request);

    /**
     *  删除机构
     * @param groupDto
     */
    void deleteGroup(GroupDto groupDto);

}
