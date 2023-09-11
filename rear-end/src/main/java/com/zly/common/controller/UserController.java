package com.zly.common.controller;


import com.zly.common.dto.UserDto;
import com.zly.common.enums.ErrorCode;
import com.zly.common.exception.BizException;
import com.zly.common.service.UserService;
import com.zly.common.utils.StringUtils;
import com.zly.common.vo.Restful;
import com.zly.common.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zly
 * @since 2023-02-17
 */
@Slf4j
@RestController
@RequestMapping("/common/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户信息列表
     */
    @RequestMapping("/getUserList")
    public Restful getList(@RequestBody UserDto userDto, HttpServletRequest request){

        log.info("getList");

        List<UserVo> userVoList = userService.getList(userDto,request);

        return Restful.ok(userVoList);
    }

    @RequestMapping("/cteateUser")
    public Restful cteateUser(@RequestBody UserDto userDto, HttpServletRequest request){

        log.info("cteateUser");

        String id = userService.cteateUser(userDto, request);

        return Restful.ok(id);
    }

    @RequestMapping("/editUser")
    public Restful editUser(@RequestBody UserDto userDto, HttpServletRequest request){

        log.info("editUser");

        userService.editUser(userDto,request);

        return Restful.ok();
    }

    @RequestMapping("/deleteUser")
    public Restful deleteUser(@RequestBody UserDto userDto, HttpServletRequest request){

        log.info("deleteUser");

        if (StringUtils.isBlank(userDto.getId())){
            throw new BizException(ErrorCode.NULL_PARAM);
        }

        userService.deleteUser(userDto,request);

        return Restful.ok();
    }

}
