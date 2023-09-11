package com.zly.common.service;

import com.zly.common.dto.UserDto;
import com.zly.common.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zly.common.vo.UserVo;

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
public interface UserService extends IService<User> {

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    UserVo getUserInfo(String id);

    /**
     * 用户登录信息
     * @param userName
     * @param pwd
     * @return
     */
    UserVo login(String userName,String pwd);

    /**
     * 获取列表
     *
     * @param userDto
     * @return
     */
    List<UserVo> getList(UserDto userDto,HttpServletRequest request);

    /**
     * 创建用户
     * @param userDto
     * @param request
     */
    String cteateUser(UserDto userDto,HttpServletRequest request);

    /**
     * 修改用户信息
     * @param userDto
     * @param request
     */
    void editUser(UserDto userDto,HttpServletRequest request);

    /**
     *删除用户
     * @param userDto
     */
    void deleteUser(UserDto userDto,HttpServletRequest request);

}
