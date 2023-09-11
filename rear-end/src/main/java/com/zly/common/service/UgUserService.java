package com.zly.common.service;

import com.zly.common.dto.UgUserDto;
import com.zly.common.entity.UgUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zly.common.vo.UgUserVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zly
 * @since 2023-04-26
 */
public interface UgUserService extends IService<UgUser> {

    /**
     * 获取用户组用户关系列表
     * @param userDto
     * @return
     */
    List<UgUserVo> getUgUserList(UgUserDto userDto);

    /**
     * 编辑用户组用户关系
     * @param userDto
     */
    void editUgUser(UgUserDto userDto);

    /**
     * 删除用户组用户关系
     * @param userDto
     */
    void deleteUgUser(UgUserDto userDto);

    List<UgUser> getUgUserByUgUser(List<String> ugIds,String uid);


}
